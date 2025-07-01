package org.example;

import java.sql.*;

public class DbConnection {
    private static final String DEFAULT_USER = "root";
    private static final String DEFAULT_PASSWORD = "root";
    private static final String DEFAULT_SERVER = "localhost";
    private static final String DEFAULT_PORT = "3306";
    private static final String DEFAULT_SCHEMA = "resortdb";
    private static Connection connection;

    public static synchronized Connection getConnection() throws SQLException {
        if (connection == null) {
            /*
            Ist in der neuen Version redundant! --> führt zu einem Fehlercode!
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
             */

            String url = String.format("%s:%s/%s", DEFAULT_SERVER, DEFAULT_PORT, DEFAULT_SCHEMA);
            String fullURL = String.format("jdbc:mysql://%s?useSSL=false", url);
            connection = DriverManager.getConnection(fullURL, DEFAULT_USER, DEFAULT_PASSWORD);
        }
        return connection;
    }

    public static void close() throws SQLException {
        if (connection != null) {
            connection.close();
            connection = null;
        }
    }
}