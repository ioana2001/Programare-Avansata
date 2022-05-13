package compulsory.server;

import compulsory.person.PersonRepository;
import lombok.extern.java.Log;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Singleton class for server
 */
@Log
public class Server {
    public static final int PORT = 8100;

    private static boolean exit = false;

    private static ServerSocket serverSocket;

    private Server() {}

    public static void init(PersonRepository personRepository) throws IOException {
        try {
            serverSocket = new ServerSocket(PORT);
            while (!exit) {
                System.out.println("Waiting for a client ...");
                Socket socket = serverSocket.accept();
                // Execute the client's request in a new thread
                new ClientThread(socket, personRepository).start();
            }
        } catch (IOException e) {
            log.warning("Server stopped ... " + e);
        } finally {
            serverSocket.close();
        }
    }

    public static void  stop() throws IOException {
        serverSocket.close();
        exit = true;
    }

}
