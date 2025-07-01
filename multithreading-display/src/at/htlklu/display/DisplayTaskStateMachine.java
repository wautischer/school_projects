package at.htlklu.display;

import at.htlklu.pojo.DisplayEntry;

import java.io.*;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DisplayTaskStateMachine extends Thread {
    private Socket socket;
    private List<DisplayEntry> displayEntries;
    private StateAuth Authenticated;
    private StatePower Status;

    private enum StateAuth {
        AUTH,
        NOAUTH
    }

    private enum StatePower {
        ON,
        OFF
    }

    public DisplayTaskStateMachine(Socket socket, List<DisplayEntry> entries) {
        this.socket = socket;
        this.displayEntries = entries;
        this.Authenticated = StateAuth.NOAUTH;
        this.Status = StatePower.OFF;
    }

    public void run() {
        try {
            InputStream inputStream = this.socket.getInputStream();
            OutputStream outputStream = this.socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            boolean running = true;

            while (running) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }

                String[] tokens = line.split(" ");
                StringBuffer buffer = new StringBuffer();

                switch (this.Authenticated) {
                    case AUTH:
                        running = handleAuth(tokens, buffer);
                        break;
                    case NOAUTH:
                        running = hanleNoAuth(tokens, buffer);
                        break;
                }

                printWriter.println(buffer.toString());
                printWriter.flush();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean hanleNoAuth(String[] tokens, StringBuffer buffer) {
        if (tokens.length == 1 && tokens[0].equalsIgnoreCase("disconnect")) {
            buffer.append("Bye Bye!");
            return false;
        } else if (tokens.length == 3 && tokens[0].equalsIgnoreCase("connect")) {
            if (logIn(tokens[1], tokens[2])) {
                buffer.append("CONNECTED");
            } else {
                buffer.append("Wrong Login! Try Again!");
            }
        } else {
            buffer.append("Login First!");
        }
        return true;
    }

    private boolean handleAuth(String[] tokens, StringBuffer buffer) {
        if (tokens.length == 1 && tokens[0].equalsIgnoreCase("disconnect")) {
            buffer.append("Bye Bye!");
            return false;
        } else if (tokens.length == 1 && tokens[0].equalsIgnoreCase("on")) {
            if (this.Status == StatePower.ON) {
                buffer.append("Display Already turned ON!");
            } else {
                this.Status = StatePower.ON;
                buffer.append("Display Turned ON!");
            }
        } else if (tokens.length == 1 && tokens[0].equalsIgnoreCase("off")) {
            if (this.Status == StatePower.OFF) {
                buffer.append("Display Already turned OFF!");
            } else {
                this.Status = StatePower.OFF;
                buffer.append("Display Turned OFF!");
            }
        } else if (this.Status == StatePower.ON) {
            if (tokens.length == 6 && tokens[0].equalsIgnoreCase("add")) {
                addEntry(tokens[1], tokens[2], tokens[3], tokens[4], tokens[5]);
                buffer.append("ADDED\n");
                for (DisplayEntry entry : getTop4Entries()) {
                    buffer.append(entry.toString()).append("\n");
                }
            } else if (tokens.length == 3 && tokens[0].equalsIgnoreCase("delete")) {
                if (deleteEntryBetween(parseStringToTime(tokens[1]), parseStringToTime(tokens[2]))) {
                    buffer.append("DELETED");
                } else {
                    buffer.append("ERROR WHILE DELETING");
                }
            } else if (tokens.length == 1 && tokens[0].equalsIgnoreCase("getAll")) {
                for (DisplayEntry entry : this.displayEntries) {
                    buffer.append(entry.toString()).append("\n");
                }
            }
        } else if (this.Status == StatePower.OFF) { buffer.append("Send ON first!");}
        return true;
    }

    private boolean deleteEntryBetween(LocalDateTime start, LocalDateTime end) {
        try {
            this.displayEntries.removeIf(entry -> {
                LocalDateTime departureTime = parseStringToTime(entry.getDepartureTime());
                return (departureTime.isAfter(start) || departureTime.isEqual(start))
                        && (departureTime.isBefore(end) || departureTime.isEqual(end));
            });
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private LocalDateTime parseStringToTime(String val) {
        return LocalDateTime.of(LocalDate.now(), LocalTime.parse(val));
    }

    private List<DisplayEntry> getTop4Entries() {
        return this.displayEntries.stream().sorted(Comparator.comparing(DisplayEntry::getDepartureTime)).limit(4).collect(Collectors.toList());
    }

    private boolean addEntry(String debTime, String from, String train, String arrvTime, String to) {
        return this.displayEntries.add(new DisplayEntry(debTime, from, train, arrvTime, to));
    }

    private boolean logIn(String val1, String val2) {
        if (val1 != null && val2 != null && val1.equalsIgnoreCase("root") && val2.equalsIgnoreCase("root")) {
            this.Authenticated = StateAuth.AUTH;
            return true;
        } else {
            return false;
        }
    }

}
