package at.htlklu.fsst.apps;

import at.htlklu.fsst.entities.Part;
import at.htlklu.fsst.persistence.StorageDAO;

import java.util.List;

public class TestApp {
    public static void main(String[] args) {
        /*
        List<Part> temp = StorageDAO.getAllParts();
        for (Part part : temp) {
            System.out.println(part);
        }
        */

        String message = StorageDAO.addPart("HTL-101-349", 20);
        System.out.println(message);

        String message2 = StorageDAO.reducePart("HTL-101-349", 1);
        System.out.println(message2);

        List<Part> searchedParts = StorageDAO.searchParts("505");
        for (Part part : searchedParts) {
            System.out.println(part);
        }
    }
}
