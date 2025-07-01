package POJO;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ParticipantStreamer {

    public List<Participant> filterParticipantsByCategory(List<Participant> participants, String category) {
        return participants.stream().filter(participant -> participant.getCategory().equals(category)).collect(Collectors.toList());
    }

    public static List<Participant> printParticipantsByCategoryAndCountry(Map<String, List<Participant>> map, String category, String countryCode) {
        Map<String, List<Participant>> tmp = new HashMap<>();
        for (Map.Entry<String, List<Participant>> entry : map.entrySet()) {
            if (entry.getKey().equalsIgnoreCase(category)) {
                tmp.put(category, entry.getValue());
            }
        }
        return tmp.get(category).stream()
                .filter(participant -> participant.getCountry().equals(countryCode))
                .sorted(
                        Comparator.comparing(
                                (Participant m) -> formatStringToDate(m.getFinishTime())
                                )
                                .thenComparing(Participant::getName)
                )
                .limit(3)
                .collect(Collectors.toList());
    }

    private static Timestamp formatStringToDate(String date) {
        String[] parts = date.split(":");
        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);
        int second = Integer.parseInt(parts[2]);
        return new Timestamp(
                LocalDate.now().getYear(),
                LocalDate.now().getMonthValue(),
                LocalDate.now().getDayOfMonth(),
                hour,
                minute,
                second,
                LocalDate.now().atStartOfDay().getNano()
        );
    }
}
