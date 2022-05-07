package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Class that creates a connection to the database
 */
public class Database {
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static final String URL =
            "jdbc:postgresql://localhost:5432/compulsory8";
    private static final String USER = "postgres";
    private static final String PASSWORD = "admin";
    private static Connection connection;

    private Database() {}

    public static Connection getConnection() {
        createConnection();
        return connection;
    }

    private static void createConnection() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.warning("Error in createConnection");
            System.err.println(e);
        }
    }

    public static void closeConnection() throws SQLException {
        connection.close();
    }

    public static void rollback(){
        try {
            connection.rollback();
        } catch (SQLException e) {
            LOGGER.warning("exception at rollback");
            e.printStackTrace();
        }
    }
}
