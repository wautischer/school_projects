package at.htlklu.fsst.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "parts")
public class Part {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "serialnr", nullable = false, length = 12)
    private String serialnr;

    @Column(name = "partname", nullable = false, length = 120)
    private String partname;

    @Column(name = "box", nullable = false, length = 10)
    private String box;

    @Column(name = "count", nullable = false)
    private Integer count;

    public Part(String serialnr, String partname, String box, Integer count) {
        this.serialnr = serialnr;
        this.partname = partname;
        this.box = box;
        this.count = count;
    }

    public Part() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSerialnr() {
        return serialnr;
    }

    public void setSerialnr(String serialnr) {
        this.serialnr = serialnr;
    }

    public String getPartname() {
        return partname;
    }

    public void setPartname(String partname) {
        this.partname = partname;
    }

    public String getBox() {
        return box;
    }

    public void setBox(String box) {
        this.box = box;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Part{" +
                "id=" + id +
                ", serialnr='" + serialnr + '\'' +
                ", partname='" + partname + '\'' +
                ", box='" + box + '\'' +
                ", count=" + count +
                '}';
    }
}