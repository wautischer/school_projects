package org.example;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ResortDAO.insertData(3,420.69, LocalDate.now());
        Map<Integer, List<List<Serializable>>> resortMap = ResortDAO.createResortMap(ResortDAO.getAllResorts());


        List<Resort> resortList = ResortDAO.getAllResorts();
        for (Resort r:resortList) {
            System.out.println(r);
        }
    }
}