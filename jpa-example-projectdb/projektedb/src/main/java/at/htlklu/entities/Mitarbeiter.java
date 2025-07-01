package at.htlklu.entities;

import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "mitarbeiter")
public class Mitarbeiter {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "nachname", nullable = false, length = 45)
    private String nachname;
    @Basic
    @Column(name = "vorname", nullable = false, length = 45)
    private String vorname;
    @Basic
    @Column(name = "geschlecht", nullable = false, length = 1)
    private String geschlecht;
    @Basic
    @Column(name = "gebdat", nullable = false)
    private Date gebdat;
    @Basic
    @Column(name = "strasse", nullable = false, length = 60)
    private String strasse;
    @Basic
    @Column(name = "plz", nullable = false, length = 6)
    private String plz;
    @Basic
    @Column(name = "ort", nullable = false, length = 30)
    private String ort;
    @Basic
    @Column(name = "bundesland", nullable = false, length = 30)
    private String bundesland;

    @OneToMany(mappedBy = "mitarbeiterByMaId",
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.LAZY)
    private Set<MaApZuord> maApZuordsById;

    public Mitarbeiter(String nachname, String vorname, String geschlecht, LocalDate gebdat, String strasse, String plz, String ort, String bundesland) {
        this.nachname = nachname;
        this.vorname = vorname;
        this.geschlecht = geschlecht;
        this.gebdat = Date.valueOf(gebdat);
        this.strasse = strasse;
        this.plz = plz;
        this.ort = ort;
        this.bundesland = bundesland;
        maApZuordsById = new HashSet<>();
    }

    public Mitarbeiter() {
        maApZuordsById = new HashSet<>();
    }

    public void addMaApZuord(MaApZuord maap) {
        this.maApZuordsById.add(maap);
        maap.setMitarbeiterByMaId(this);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getGeschlecht() {
        return geschlecht;
    }

    public void setGeschlecht(String geschlecht) {
        this.geschlecht = geschlecht;
    }

    public LocalDate getGebdat() {
        return gebdat.toLocalDate();
    }

    public void setGebdat(LocalDate gebdat) {
        this.gebdat = Date.valueOf(gebdat);
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
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

    public String getBundesland() {
        return bundesland;
    }

    public void setBundesland(String bundesland) {
        this.bundesland = bundesland;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mitarbeiter that = (Mitarbeiter) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (nachname != null ? !nachname.equals(that.nachname) : that.nachname != null) return false;
        if (vorname != null ? !vorname.equals(that.vorname) : that.vorname != null) return false;
        if (geschlecht != null ? !geschlecht.equals(that.geschlecht) : that.geschlecht != null) return false;
        if (gebdat != null ? !gebdat.equals(that.gebdat) : that.gebdat != null) return false;
        if (strasse != null ? !strasse.equals(that.strasse) : that.strasse != null) return false;
        if (plz != null ? !plz.equals(that.plz) : that.plz != null) return false;
        if (ort != null ? !ort.equals(that.ort) : that.ort != null) return false;
        if (bundesland != null ? !bundesland.equals(that.bundesland) : that.bundesland != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nachname != null ? nachname.hashCode() : 0);
        result = 31 * result + (vorname != null ? vorname.hashCode() : 0);
        result = 31 * result + (geschlecht != null ? geschlecht.hashCode() : 0);
        result = 31 * result + (gebdat != null ? gebdat.hashCode() : 0);
        result = 31 * result + (strasse != null ? strasse.hashCode() : 0);
        result = 31 * result + (plz != null ? plz.hashCode() : 0);
        result = 31 * result + (ort != null ? ort.hashCode() : 0);
        result = 31 * result + (bundesland != null ? bundesland.hashCode() : 0);
        return result;
    }

    public Set<MaApZuord> getMaApZuordsById() {
        return maApZuordsById;
    }

    public void setMaApZuordsById(Set<MaApZuord> maApZuordsById) {
        this.maApZuordsById = maApZuordsById;
    }

    @Override
    public String toString() {
        return "Mitarbeiter{" +
                "id=" + id +
                ", nachname='" + nachname + '\'' +
                ", vorname='" + vorname + '\'' +
                ", geschlecht='" + geschlecht + '\'' +
                ", gebdat=" + gebdat +
                ", strasse='" + strasse + '\'' +
                ", plz='" + plz + '\'' +
                ", ort='" + ort + '\'' +
                ", bundesland='" + bundesland + '\'' +
                '}';
    }
}
