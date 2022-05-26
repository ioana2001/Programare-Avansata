package homework.server;

import lombok.extern.java.Log;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

@Log
public class Server {
    private static final int PORT = 8100;

    private static boolean exit = false;

    private static final List<ClientThread> clients = new ArrayList<>();

    private static ServerSocket serverSocket;

    private Server() {
    }

    public static void init() {
        try {
            serverSocket = new ServerSocket(PORT);
            while (!exit) {
                System.out.println("Waiting for a client ...");
                Socket socket = serverSocket.accept();
                // Execute the client's request in a new thread
                ClientThread clientThread = new ClientThread(socket);
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
