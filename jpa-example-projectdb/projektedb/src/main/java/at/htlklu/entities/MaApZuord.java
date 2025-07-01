package at.htlklu.entities;

import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "ma_ap_zuord")
public class MaApZuord {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "std", nullable = false)
    private Integer std;
    @Basic
    @Column(name = "datum", nullable = false)
    private Date datum;
    @Basic
    @Column(name = "anmerkung", nullable = false, length = 200)
    private String anmerkung;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_id", referencedColumnName = "id", nullable = false)
    private Mitarbeiter mitarbeiterByMaId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ap_id", referencedColumnName = "id", nullable = false)
    private Arbeitspakete arbeitspaketeByApId;

    public MaApZuord(int std, LocalDate datum, String anmerkung, Mitarbeiter mitarbeiterByMaId, Arbeitspakete arbeitspaketeByApId) {
        this.std = std;
        this.datum = Date.valueOf(datum);
        this.anmerkung = anmerkung;
        mitarbeiterByMaId.addMaApZuord(this);
        arbeitspaketeByApId.addMaApZuord(this);
    }

    public MaApZuord() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStd() {
        return std;
    }

    public void setStd(Integer std) {
        this.std = std;
    }

    public LocalDate getDatum() {
        return datum.toLocalDate();
    }

    public void setDatum(LocalDate datum) {
        this.datum = Date.valueOf(datum);
    }

    public String getAnmerkung() {
        return anmerkung;
    }

    public void setAnmerkung(String anmerkung) {
        this.anmerkung = anmerkung;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MaApZuord maApZuord = (MaApZuord) o;

        if (id != null ? !id.equals(maApZuord.id) : maApZuord.id != null) return false;
        if (std != null ? !std.equals(maApZuord.std) : maApZuord.std != null) return false;
        if (datum != null ? !datum.equals(maApZuord.datum) : maApZuord.datum != null) return false;
        if (anmerkung != null ? !anmerkung.equals(maApZuord.anmerkung) : maApZuord.anmerkung != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (std != null ? std.hashCode() : 0);
        result = 31 * result + (datum != null ? datum.hashCode() : 0);
        result = 31 * result + (anmerkung != null ? anmerkung.hashCode() : 0);
        return result;
    }

    public Mitarbeiter getMitarbeiterByMaId() {
        return mitarbeiterByMaId;
    }

    public void setMitarbeiterByMaId(Mitarbeiter mitarbeiterByMaId) {
        this.mitarbeiterByMaId = mitarbeiterByMaId;
    }

    public Arbeitspakete getArbeitspaketeByApId() {
        return arbeitspaketeByApId;
    }

    public void setArbeitspaketeByApId(Arbeitspakete arbeitspaketeByApId) {
        this.arbeitspaketeByApId = arbeitspaketeByApId;
    }

    @Override
    public String toString() {
        return "MaApZuord{" +
                "id=" + id +
                ", std=" + std +
                ", datum=" + datum +
                ", anmerkung='" + anmerkung + '\'' +
                '}';
    }
}
