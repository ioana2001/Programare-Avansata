package compulsory.server;

import compulsory.repository.MessageRepository;
import compulsory.repository.PersonRepository;
import lombok.extern.java.Log;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Singleton class for server
 */
@Log
public class Server {
    private static final int PORT = 8100;

    private static boolean exit = false;

    private static final List<ClientThread> clients = new ArrayList<>();

    private static ServerSocket serverSocket;

    private Server() {
    }

    public static void init(PersonRepository personRepository, MessageRepository messageRepository) {
        try {
            serverSocket = new ServerSocket(PORT);
            while (!exit) {
                System.out.println("Waiting for a client ...");
                Socket socket = serverSocket.accept();
                // Execute the client's request in a new thread
                ClientThread clientThread = new ClientThread(socket, personRepository, messageRepository);
                clients.add(clientThread);
                clientThread.start();
            }
        } catch (IOException e) {
            log.warning("Server stopped ... " + e);
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                log.warning("Server stopped ... " + e);
            }
        }
    }

    public static void stop() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            log.warning("Server stopped ... " + e);
        }
        exit = true;
    }

}
