package at.htlklu.fsst.multithreading;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class StorageServer {

    private final int PORT = 12312;

    private void start() throws IOException {
        boolean isRunning = true;

        ServerSocket serversocket = new ServerSocket(PORT);

        System.out.println("Server running on Port " + PORT);

        while (isRunning) {
            Socket socket = serversocket.accept();
            Thread t = new StorageThread(socket);
            t.start();
        }
    }

    public static void main(String[] args) throws IOException {
        StorageServer server = new StorageServer();
        server.start();
    }
}
