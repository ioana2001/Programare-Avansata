package main.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client {

    public static void main(String[] args) throws IOException {

        // The server's IP address
        String serverAddress = "127.0.0.1";
        // The server's port
        int PORT = 8100;

        try (Socket socket = new Socket(serverAddress, PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                String command = reader.readLine();

                // Send a request to the server
                out.println(command);

                if (command.equals("exit")) {
                    socket.close();
                    break;
                }

                // Wait the response from the server
                String response = in.readLine();
                System.out.println(response);
                response = in.readLine();
                System.out.println(response);

                if (command.equals("stop")) {
                    break;
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("No server listening... " + e);
        } catch (SocketException e) {
            System.err.println("Server timeout... " + e);
        }
    }

}
