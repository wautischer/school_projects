import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FileProcessor {
    public static final String PATH = "data/";

    public static void main(String[] args) {
        List<PVUnit> defaultList = readFile("PVData.csv", "Klagenfurt am Wörthersee");
        List<PVUnit> sortedList = sortFile(defaultList);
        writeFile("PVUnits_Klagenfurt.txt", sortedList);
    }

    public static List<PVUnit> sortFile(List<PVUnit> toSort) {
        return toSort.stream().sorted(
                Comparator
                        .comparing(PVUnit::getKwpeek)
                        .thenComparing(PVUnit::getStrasse)
                        .thenComparing(PVUnit::getHausnummer)
        ).toList();
    }

    public static List<PVUnit> readFile(String fileName, String ort) {
        List<PVUnit> tmp = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(PATH + fileName), StandardCharsets.ISO_8859_1))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                PVUnit tmpPVunit = PVUnit.getPVUnitFromLine(line);
                if (tmpPVunit.getOrt().equalsIgnoreCase(ort)) {
                    tmp.add(tmpPVunit);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return tmp;
    }

    public static void writeFile(String fileName, List<PVUnit> listToWrite) {
        double summe = 0;
        int anzahl = 0;
        try (PrintWriter pr = new PrintWriter(new BufferedWriter(new FileWriter(PATH + fileName, true)))) {
            for (PVUnit pvUnit : listToWrite) {
                pr.println(pvUnit);
                summe += pvUnit.getKwpeek();
                anzahl++;
            }
            pr.printf("Summe der Leistung, installiert in ausgewählter Stadt: %.2f kWp%n", summe);
            pr.printf("Durchschnittliche Spitzenleistung der installierten Leistung in der ausgewählten Stadt: %.2f kWp", (summe / anzahl));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}