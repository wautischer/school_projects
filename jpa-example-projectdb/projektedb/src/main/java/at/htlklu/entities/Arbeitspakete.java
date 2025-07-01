package at.htlklu.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "arbeitspakete")
public class Arbeitspakete {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "code", nullable = false, length = 5)
    private String code;
    @Basic
    @Column(name = "Bezeichnung", nullable = false, length = 50)
    private String bezeichnung;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pr_nr", referencedColumnName = "nr", nullable = false)
    private Projekte projekteByPrNr;

    @OneToMany(mappedBy = "arbeitspaketeByApId",
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.LAZY)
    private Set<MaApZuord> maApZuordsById;

    public Arbeitspakete(String code, String bezeichnung, Projekte projekteByPrNr) {
        this.code = code;
        this.bezeichnung = bezeichnung;
        maApZuordsById = new HashSet<>();

        projekteByPrNr.addArbeitspaket(this);
    }

    public Arbeitspakete(){
        maApZuordsById = new HashSet<>();
    }

    public void addMaApZuord(MaApZuord maap) {
        this.maApZuordsById.add(maap);
        maap.setArbeitspaketeByApId(this);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Arbeitspakete that = (Arbeitspakete) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (bezeichnung != null ? !bezeichnung.equals(that.bezeichnung) : that.bezeichnung != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (bezeichnung != null ? bezeichnung.hashCode() : 0);
        return result;
    }

    public Projekte getProjekteByPrNr() {
        return projekteByPrNr;
    }

    public void setProjekteByPrNr(Projekte projekteByPrNr) {
        this.projekteByPrNr = projekteByPrNr;
    }

    public Set<MaApZuord> getMaApZuordsById() {
        return maApZuordsById;
    }

    public void setMaApZuordsById(Set<MaApZuord> maApZuordsById) {
        this.maApZuordsById = maApZuordsById;
    }

    @Override
    public String toString() {
        return "Arbeitspakete{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", bezeichnung='" + bezeichnung + '\'' +
                '}';
    }
}
