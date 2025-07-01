package at.htlklu.pojo;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.LocalDate;
public class Main {
    public static void main(String[] args) {
        String timeString = "21:21";

        LocalTime localTime = LocalTime.parse(timeString);

        LocalDate currentDate = LocalDate.now();

        LocalDateTime localDateTime = LocalDateTime.of(currentDate, localTime);

        System.out.println("LocalDateTime: " + localTime);
    }
}
