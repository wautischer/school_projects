package org.example;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ResortDAO {
    static Connection connection;
    static Statement statement;

    public static List<Resort> getAllResorts() {
        List<Resort> resortList = new ArrayList<>();

        try {
            connection = DbConnection.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select r.resort_id, r.resort_name, rv.height, rv.date from resort as r, resort_value as rv where r.resort_id = rv.resort_id");

            while (resultSet.next()) {

                int resortID = resultSet.getInt(1);
                String resortName = resultSet.getString(2);
                double resortHeight = resultSet.getDouble(3);

                LocalDate resortDate = resultSet.getDate(4).toLocalDate();

                Resort newResort = new Resort(resortID, resortName, resortHeight, resortDate);

                resortList.add(newResort);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resortList;

    }

    public static String insertData(int resortID, double newHeight, LocalDate newDate) {

        String status = "";

        try {
            connection = DbConnection.getConnection();
            statement = connection.createStatement();

            int rowsAffected = statement.executeUpdate("INSERT INTO resort_value (resort_id, height, date) VALUES (" + resortID + ", " + newHeight + ", '" + newDate + "')");

            if (rowsAffected > 0) {
                status = "Einfügen erfolgreich! Anzahl der betroffenen Zeilen: " + rowsAffected;
            } else {
                status = "Fehler beim Einfügen";
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return status;
    }

    public static Map<Integer, List<List<Serializable>>> createResortMap(List<Resort> resortList) {
        return resortList.stream().collect(
                Collectors.groupingBy(
                        Resort::getResort_id, Collectors.mapping(
                                resort -> List.of(Double.toString(resort.getHeight()), resort.getDate()),
                                Collectors.toList())));
    }
}

