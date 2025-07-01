import POJO.Participant;
import POJO.ParticipantStreamer;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FileProcessor {
    public static final String PATH = "data/";

    public static void main(String[] args) {
        ParticipantStreamer streamer = new ParticipantStreamer();

        List<Participant> tmp = readCSVFile("Marathon_2023_data.csv");
        List<Participant> filteredList = streamer.filterParticipantsByCategory(tmp,"F20");

        Map<String, List<Participant>> map = buildParticipantMap(tmp);
        List<Participant> moreFilteredList = ParticipantStreamer.printParticipantsByCategoryAndCountry(map,"F20", "AUT");
        writeFile("output2.txt", moreFilteredList);
    }

    public static Map<String, List<Participant>> buildParticipantMap(List<Participant> participants) {
        return participants.stream().collect(Collectors.groupingBy(Participant::getCategory));
    }

    public static List<Participant> readCSVFile(String filename) {
        List<Participant> participants = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(PATH + filename), StandardCharsets.ISO_8859_1))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                participants.add(new Participant().getParticipantFromCSV(line));
            }
            return participants;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeFile(String filename, List<Participant> participants) {
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(PATH + filename, true)))) {
            pw.println(new Timestamp(System.currentTimeMillis()));
            for (Participant p : participants) {
                pw.println(p);
            }
            pw.println("");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
