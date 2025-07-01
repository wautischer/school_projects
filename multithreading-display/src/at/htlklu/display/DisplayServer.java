package at.htlklu.display;

import at.htlklu.pojo.DisplayEntry;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DisplayServer {
    public static void main(String[] args) {
        try {
            final int port = 9999;
            Socket socket = null;
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Binding port " + port);

            List<DisplayEntry> DisplayEntries = Collections.synchronizedList(new ArrayList<>());

            while (true) {
                socket = serverSocket.accept();
                (new DisplayTaskStateMachine(socket, DisplayEntries)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
