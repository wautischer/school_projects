package at.htlklu.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "projekte")
public class Projekte {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "nr", nullable = false)
    private Integer nr;
    @Basic
    @Column(name = "titel", nullable = false, length = 200)
    private String titel;
    @Basic
    @Column(name = "code", nullable = false, length = 10)
    private String code;

    @OneToMany(mappedBy = "projekteByPrNr",
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.EAGER)
    private Set<Arbeitspakete> arbeitspaketesByNr;

    public Projekte(String titel, String code) {
        this.titel = titel;
        this.code = code;
        arbeitspaketesByNr = new HashSet<>();
    }

    public Projekte() {
    }

    public void addArbeitspaket(Arbeitspakete ap) {
        this.arbeitspaketesByNr.add(ap);
        ap.setProjekteByPrNr(this);
    }

    public Integer getNr() {
        return nr;
    }

    public void setNr(Integer nr) {
        this.nr = nr;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Projekte projekte = (Projekte) o;

        if (nr != null ? !nr.equals(projekte.nr) : projekte.nr != null) return false;
        if (titel != null ? !titel.equals(projekte.titel) : projekte.titel != null) return false;
        if (code != null ? !code.equals(projekte.code) : projekte.code != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nr != null ? nr.hashCode() : 0;
        result = 31 * result + (titel != null ? titel.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        return result;
    }

    public Set<Arbeitspakete> getArbeitspaketesByNr() {
        return arbeitspaketesByNr;
    }

    public void setArbeitspaketesByNr(Set<Arbeitspakete> arbeitspaketesByNr) {
        this.arbeitspaketesByNr = arbeitspaketesByNr;
    }

    @Override
    public String toString() {
        return "Projekte{" +
                "nr=" + nr +
                ", titel='" + titel + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
