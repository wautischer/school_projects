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

public class DisplayTask extends Thread {
    private Socket socket;
    private List<DisplayEntry> displayEntries;
    private Boolean AUTH;
    private Boolean STATUS;

    public DisplayTask(Socket socket, List<DisplayEntry> entries) {
        this.socket = socket;
        this.displayEntries = entries;
        this.AUTH = false;
        this.STATUS = false;
    }



    public void run() {
        try (PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            String input;
            StringBuffer output = new StringBuffer();
            String[] tokens;
            String command;
            String val1 = null;
            String val2 = null;
            String train = null;
            String arrivalTime = null;
            String destination = null;

            while ((input = in.readLine()) != null) {
                tokens = input.split(" ");
                command = tokens[0];
                if (tokens.length > 1) {
                    val1 = tokens[1];
                    if (tokens.length > 2) {
                        val2 = tokens[2];
                    }
                    if (tokens.length > 3) {
                        train = tokens[3];
                    }
                    if (tokens.length > 4) {
                        arrivalTime = tokens[4];
                    }
                    if (tokens.length >= 5) {
                        destination = tokens[5];
                    }
                }

                if (command.equalsIgnoreCase("CONNECT") && !this.AUTH) {
                    if (logIn(val1, val2)) {
                        out.append("CONNECTED");
                    } else {
                        out.append("INVALID LOGIN");
                    }
                } else if (command.equalsIgnoreCase("ON")) {
                    if (AUTH) {
                        if (!STATUS) {
                            STATUS = true;
                            out.append("DISPLAY ON");
                        } else {
                            out.append("DISPLAY ALREADY ON");
                        }
                    } else {
                        out.append("LOGIN FIRST");
                    }
                } else if (command.equalsIgnoreCase("OFF")) {
                    if (AUTH) {
                        if (STATUS) {
                            STATUS = false;
                            out.append("DISPLAY OFF");
                        } else {
                            out.append("DISPLAY ALREADY OFF");
                        }
                    } else {
                        out.append("LOGIN FIRST");
                    }
                } else if (command.equalsIgnoreCase("ADD")) {
                    if (AUTH) {
                        if (STATUS) {
                            addEntry(val1, val2, train, arrivalTime, destination);
                            out.append("ADDED\n");
                            for (DisplayEntry entry : getTop4Entries()) {
                                out.append(entry.toString() + "\n");
                            }
                        } else {
                            out.append("SEND ON FIRST");
                        }
                    } else {
                        out.append("LOGIN FIRST");
                    }
                } else if (command.equalsIgnoreCase("DELETE")) {
                    if (AUTH) {
                        if (STATUS) {
                            if (deleteEntryBetween(parseStringToTime(val1), parseStringToTime(val2))) {
                                out.append("DELETED");
                            } else {
                                out.append("ERROR WHILE DELETING");
                            }
                        } else {
                            out.append("SEND ON FIRST");
                        }
                    } else {
                        out.append("LOGIN FIRST");
                    }
                } else if (command.equalsIgnoreCase("DISCONNECT")) {
                    out.append("Bye Bye!");
                    out.println(output);
                    out.flush();
                    break;
                } else if (command.equalsIgnoreCase("ALL")) {
                    for (DisplayEntry entry : this.displayEntries) {
                        out.append(entry.toString() + "\n");
                    }
                }
                out.println(output);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
            this.AUTH = true;
            return true;
        } else {
            return false;
        }
    }
}
