package homework.server;

import homework.entity.Person;
import homework.repository.PersonRepository;
import lombok.extern.java.Log;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;

@Log
public class ClientThread extends Thread {
    private static final Integer TIMEOUT_TIME = 40_000;
    private Socket socket = null;
    private String loggedPerson = "";
    private static final String uri = "http://localhost:8088/persons";

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {

        try {
            // Get the request from the input stream: client → server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // Send the response to the output stream: server → client
            PrintWriter out = new PrintWriter(socket.getOutputStream());

            while (true) {
                socket.setSoTimeout(TIMEOUT_TIME);

                String request = in.readLine();
                if (request.equals("exit")) break;
                String[] parsedCommand = request.split(" ");

                String result = switch (parsedCommand[0]) {
                    case "register" -> register(parsedCommand[1]);
                    case "login" -> login(parsedCommand[1]);
                    case "friend" -> friend(parsedCommand);
                    case "stop" -> "Server stopped ...";
                    default -> "Unknown command";
                };

                out.println("Server received the request " + parsedCommand[0]);
                out.println(result);
                out.flush();

                if (parsedCommand[0].equals("stop")) {
                    socket.close();
                    Server.stop();
                    break;
                }
            }
        } catch (SocketTimeoutException e) {
            System.out.printf("%d seconds passed since last request. Timed out.\n", TIMEOUT_TIME / 1000);
        } catch (IOException e) {
            log.warning("IOException in ClientThread");
            System.err.println("Communication error... " + e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                log.warning("IOException in ClientThread");
                System.err.println(e);
            }
        }
    }

    /**
     * register the client in the database
     */
    private String register(String name) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>("{ \"name\": \"" + name + "\" }", headers);
        ResponseEntity<String> response = restTemplate.postForEntity(uri, request, String.class);
        return "Person registered: " + name;
    }

    private String login(String name) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(uri + '/' + name, String.class);
        if(response.getStatusCode() == HttpStatus.OK) {
            loggedPerson = name;
        }
        return response.getBody();
    }

    /**
     * adds friends to the logged user
     */
    private String friend(String[] command) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        if (loggedPerson.isEmpty()) return "You are not logged in!";
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < command.length; i++) {

            HttpEntity<String> request = new HttpEntity<>("{ \"name\": \"" + command[i] + "\" }", headers);
            ResponseEntity<String> response = restTemplate.postForEntity(uri + '/' + loggedPerson + "/friends" , request, String.class);

            if(response.getStatusCode().is2xxSuccessful())
                result.append(response.getBody()).append("; ");
            else
                result.append("Person ").append(command[i]).append(" not found").append("; ");
        }

        return result.toString();
    }

}
