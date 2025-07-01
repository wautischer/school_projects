package org.example;

import java.time.LocalDate;

public class Resort {
    private int resort_id;
    private String resort_name;
    private double height;
    private LocalDate date;

    public Resort(int resort_id, String resort_name, double height, LocalDate date) {
        this.resort_id = resort_id;
        this.resort_name = resort_name;
        this.height = height;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Resort{" +
                "resort_id=" + resort_id +
                ", resort_name='" + resort_name + '\'' +
                ", height=" + height +
                ", date=" + date +
                '}';
    }

    public int getResort_id() {
        return resort_id;
    }

    public void setResort_id(int resort_id) {
        this.resort_id = resort_id;
    }

    public String getResort_name() {
        return resort_name;
    }

    public void setResort_name(String resort_name) {
        this.resort_name = resort_name;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
