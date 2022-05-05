package DAO;

import database.Database;

import java.sql.*;
import java.util.logging.Logger;


/**
 * DAO for table continents from the database
 */
public class ContinentDAO {
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * @param name -name of the continent
     * insert into table continents this continent
     */
    public void create(String name) throws SQLException {
        Connection con = Database.getConnection();
        if (findByName(name) == null) {
            try (PreparedStatement pstmt = con.prepareStatement("insert into continents (name) values (?)")) {
                pstmt.setString(1, name);
                pstmt.executeUpdate();
                con.commit();
            }
        }
    }

    /**
     * @param name -name of the continent for witch I am searching the id
     * @return -id of the continent with this name
     */
    public Integer findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("select id from continents where name='" + name + "'")) {
            return rs.next() ? rs.getInt(1) : null;
        }
    }

    /**
     * @param id -id of the continent for witch I am searching the name
     * @return -name of the continent with this id
     */
    public String findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("select name from continents where id='" + id + "'")) {
            return rs.next() ? rs.getString(1) : null;
        }

    }

    public void printContinents() {
        Connection con = Database.getConnection();
        PreparedStatement p = null;
        ResultSet rs = null;
        try {
            String sql = "select * from continents";
            p = con.prepareStatement(sql);
            rs = p.executeQuery();

            System.out.println("Table continents: ");
            System.out.println("id\t\tname");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                System.out.println(id + "\t\t" + name);
            }
        } catch (SQLException e) {
            LOGGER.warning("SQLException");
            e.printStackTrace();
        }
    }
}
