public class PVUnit {
    private Integer id;
    private double kwpeek;
    private String wechselrichter;
    private String plz;
    private String ort;
    private String strasse;
    private String hausnummer;

    public PVUnit(Integer id, double kwpeek, String wechselrichter, String plz, String ort, String strasse, String hausnummer) {
        this.id = id;
        this.kwpeek = kwpeek;
        this.wechselrichter = wechselrichter;
        this.ort = ort;
        this.plz = plz;
        this.strasse = strasse;
        this.hausnummer = hausnummer;
    }

    public PVUnit() {
    }

    @Override
    public String toString() {
        return kwpeek +"kWp ("+wechselrichter+") in "+ort+", "+ strasse + " "+hausnummer;
    }

    public static PVUnit getPVUnitFromLine(String line) {
        String[] parts = line.split(";");
        return new PVUnit(
                Integer.valueOf(parts[0]),
                Double.parseDouble(parts[1]),
                parts[2],
                parts[3],
                parts[4],
                parts[5],
                parts[6]
        );
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getKwpeek() {
        return kwpeek;
    }

    public void setKwpeek(double kwpeek) {
        this.kwpeek = kwpeek;
    }

    public String getWechselrichter() {
        return wechselrichter;
    }

    public void setWechselrichter(String wechselrichter) {
        this.wechselrichter = wechselrichter;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getHausnummer() {
        return hausnummer;
    }

    public void setHausnummer(String hausnummer) {
        this.hausnummer = hausnummer;
    }
}
