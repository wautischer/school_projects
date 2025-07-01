package at.htlklu.fsst.multithreading;

import at.htlklu.fsst.persistence.StorageDAO;

import java.net.Socket;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class StorageThread extends Thread {
    private Socket socket;

    public StorageThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        super.run();
        System.out.println("--> Client connected <--");

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            bw.write("Connection to HTL Door server established.");
            bw.newLine();
            bw.flush();

            String input = "";

            while ((input = br.readLine()) != null) {

                if (input.contains("EXIT")) {
                    bw.write("BYE!");
                    bw.newLine();
                    bw.flush();
                    break;
                }

                input = filterBackspace(input);
                String[] tokens = input.split(" ");

                if (tokens[0].equalsIgnoreCase("ADD")) {
                    String sn = tokens[1];
                    String count = tokens[2];
                    bw.write(StorageDAO.addPart(sn, Integer.parseInt(count)));
                }

                if (tokens[0].equalsIgnoreCase("REDUCE")) {
                    String sn = tokens[1];
                    String count = tokens[2];
                    bw.write(StorageDAO.reducePart(sn, Integer.parseInt(count)));
                }

                bw.newLine();
                bw.flush();
            }

            bw.flush();
            bw.close();
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String filterBackspace(String input) {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char localChar = input.charAt(i);

            boolean isUpperCase = localChar >= 'A' && localChar <= 'Z';
            boolean isLowerCase = localChar >= 'a' && localChar <= 'z';
            boolean isNumber = localChar >= '0' && localChar <= '9';
            boolean isDash = localChar == '-';

            if (isUpperCase || isLowerCase || isNumber || localChar == ' ' || isDash) {
                output.append(localChar);
            } else {
                output = new StringBuilder(deleteLast(output.toString()));
            }
        }

        return output.toString();
    }

    private static String deleteLast(String input) {
        if (input.length() > 0) {
            return input.substring(0, input.length() - 1);
        }
        return input;
    }
}



