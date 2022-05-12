package homework.jdbc.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * class for connection to database from  postgres
 */
public class Database {
    private static final String URL = "jdbc:postgresql://localhost:5432/compulsory8";
    private static final String USER = "postgres";
    private static final String PASSWORD = "admin";
    private static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Database() {
    }

    public static Connection getConnection() {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void rollback() {
        try {
            connection.setAutoCommit(false);
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}