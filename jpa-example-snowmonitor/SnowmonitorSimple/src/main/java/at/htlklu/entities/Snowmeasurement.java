package at.htlklu.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name="snowmeasurement") //sonst wird versucht eine neue Tabelle zu erstellen.
public class Snowmeasurement {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idsnowmeasurement", nullable = false)
    private Integer idsnowmeasurement;
    @Basic
    @Column(name = "stationname", nullable = false, length = 45)
    private String stationname;
    @Basic
    @Column(name = "snowheight", nullable = false, precision = 0)
    private Double snowheight;
    @Basic
    @Column(name = "datetimestamp", nullable = false)
    private Timestamp datetimestamp;
    @Basic
    @Column(name = "comment", nullable = true, length = 45)
    private String comment;

    public Snowmeasurement() {

    }

    public Snowmeasurement( String stationname, Double snowheight, LocalDateTime datetimestamp) {

        this.stationname = stationname;
        this.snowheight = snowheight;
        this.datetimestamp = Timestamp.valueOf(datetimestamp);

    }

    public Integer getIdsnowmeasurement() {
        return idsnowmeasurement;
    }

    public void setIdsnowmeasurement(Integer idsnowmeasurement) {
        this.idsnowmeasurement = idsnowmeasurement;
    }

    public String getStationname() {
        return stationname;
    }

    public void setStationname(String stationname) {
        this.stationname = stationname;
    }

    public Double getSnowheight() {
        return snowheight;
    }

    public void setSnowheight(Double snowheight) {
        this.snowheight = snowheight;
    }

    public LocalDateTime getDatetimestamp() {
        return datetimestamp.toLocalDateTime();
    }

    public void setDatetimestamp(LocalDateTime datetimestamp) {
        this.datetimestamp = Timestamp.valueOf(datetimestamp);
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Snowmeasurement that = (Snowmeasurement) o;

        if (idsnowmeasurement != null ? !idsnowmeasurement.equals(that.idsnowmeasurement) : that.idsnowmeasurement != null)
            return false;
        if (stationname != null ? !stationname.equals(that.stationname) : that.stationname != null) return false;
        if (snowheight != null ? !snowheight.equals(that.snowheight) : that.snowheight != null) return false;
        if (datetimestamp != null ? !datetimestamp.equals(that.datetimestamp) : that.datetimestamp != null)
            return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idsnowmeasurement != null ? idsnowmeasurement.hashCode() : 0;
        result = 31 * result + (stationname != null ? stationname.hashCode() : 0);
        result = 31 * result + (snowheight != null ? snowheight.hashCode() : 0);
        result = 31 * result + (datetimestamp != null ? datetimestamp.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Snowmeasurement{" +
                "idsnowmeasurement=" + idsnowmeasurement +
                ", stationname='" + stationname + '\'' +
                ", snowheight=" + snowheight +
                ", datetimestamp=" + getDatetimestamp() +
                ", comment='" + comment + '\'' +
                '}';
    }
}
