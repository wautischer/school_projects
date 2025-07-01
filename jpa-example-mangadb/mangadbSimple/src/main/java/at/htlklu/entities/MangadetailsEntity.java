package at.htlklu.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "mangadetails")
public class MangadetailsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idmangadetails", nullable = false)
    private Integer idmangadetails;
    @Basic
    @Column(name = "name", nullable = true, length = 45)
    private String name;
    @Basic
    @Column(name = "price", nullable = true, precision = 0)
    private Double price;
    @Basic
    @Column(name = "genre", nullable = true, length = 45)
    private String genre;
    @Basic
    @Column(name = "pages", nullable = true)
    private Integer pages;
    @Basic
    @Column(name = "inserttimestamp", nullable = true)
    private Timestamp inserttimestamp;

    public MangadetailsEntity(){

    }

    public MangadetailsEntity(String name, double price, String genre, Integer pages, LocalDateTime inserttimestamp){
        this.name = name;
        this.price = price;
        this.genre = genre;
        this.pages = pages;
        this.inserttimestamp = Timestamp.valueOf(inserttimestamp);
    }

    public int getIdmangadetails() {
        return idmangadetails;
    }

    public void setIdmangadetails(int idmangadetails) {
        this.idmangadetails = idmangadetails;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public LocalDateTime getInserttimestamp() {
        return inserttimestamp.toLocalDateTime();
    }

    public void setInserttimestamp(LocalDateTime inserttimestamp) {
        this.inserttimestamp = Timestamp.valueOf(inserttimestamp);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MangadetailsEntity that = (MangadetailsEntity) o;

        if (idmangadetails != that.idmangadetails) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (genre != null ? !genre.equals(that.genre) : that.genre != null) return false;
        if (pages != null ? !pages.equals(that.pages) : that.pages != null) return false;
        if (inserttimestamp != null ? !inserttimestamp.equals(that.inserttimestamp) : that.inserttimestamp != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idmangadetails;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        result = 31 * result + (pages != null ? pages.hashCode() : 0);
        result = 31 * result + (inserttimestamp != null ? inserttimestamp.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MangadetailsEntity{" +
                "idmangadetails=" + idmangadetails +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", genre='" + genre + '\'' +
                ", pages=" + pages +
                ", inserttimestamp=" + inserttimestamp +
                '}';
    }
}
