package compulsory.server;

import compulsory.person.Person;
import compulsory.person.PersonRepository;
import lombok.extern.java.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * A thread representing a connection with a client
 */
@Log
public class ClientThread extends Thread {
    private Socket socket = null;
    private PersonRepository personRepository;

    private Person person;

    public ClientThread(Socket socket, PersonRepository personRepository) {
        this.socket = socket;
        this.personRepository = personRepository;
    }

    public void run() {

        try {
            // Get the request from the input stream: client → server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // Send the response to the output stream: server → client
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            while (true) {
                String request = in.readLine();
                if(request.equals("exit"))
                    break;
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

                if (parsedCommand[0].equals("stop")){
                    socket.close();
                    Server.stop();
                    break;
                }
            }
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
        personRepository.save(new Person(name));
        return "Person registered: " + name;
    }

    private String login(String name) {
        if (personRepository.findFirstByName(name) == null) {
            return "User does not exist";
        } else {
            person = personRepository.findFirstByName(name);
            return "Login successful";
        }
    }

    /**
     * adds friends to the logged user
     */
    private String friend(String[] command) {
        if(person == null)
            return "You are not logged in!";
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < command.length; i++) {
            Person friend = personRepository.findFirstByName(command[i]);
            if (friend == null) {
                result.append("User ").append(command[i]).append(" does not exist; ");
            } else {
                result.append("You added ").append(command[i]).append("; ");
                person.addFriend(friend);
            }
        }
        personRepository.save(person);
        return result.toString();
    }

}
