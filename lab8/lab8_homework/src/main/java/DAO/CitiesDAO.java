package DAO;

import database.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * DAO for table cities from the database
 */
public class CitiesDAO {
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * inserts into cities table the given values
     */
    public void create(String name, boolean capital, double latitude, double longitude, int id_country) throws SQLException {
        Connection con = Database.getConnection();
        if (findByName(name) == null) {
            try (PreparedStatement pstmt = con.prepareStatement("insert into cities (name,capital,latitude,longitude,id_country) values (?,?,?,?,?)")) {
                pstmt.setString(1, name);
                pstmt.setBoolean(2, capital);
                pstmt.setDouble(3, latitude);
                pstmt.setDouble(4, longitude);
                pstmt.setInt(5, id_country);
                pstmt.executeUpdate();
                con.commit();
            }
        }
    }

    /**
     * @param name -name of the city for witch I am searching the id
     * @return -id of the city with this name
     */
    public Integer findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("select id from cities where name='" + name + "'")) {
            return rs.next() ? rs.getInt(1) : null;
        }
    }

    /**
     * @param id -id of the city for witch I am searching the name
     * @return -name of the city with this id
     */
    public String findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("select name from cities where id='" + id + "'")) {
            return rs.next() ? rs.getString(1) : null;
        }

    }

    public void printCities() {
        Connection con = Database.getConnection();
        PreparedStatement p = null;
        ResultSet rs = null;
        try {

            String sql = "select * from cities";
            p = con.prepareStatement(sql);
            rs = p.executeQuery();

            System.out.println("Table continents: ");
            System.out.println("id\t\tname\t\tcapital\t\tlatitude\t\tlongitude\t\tid_country");
            while (rs.next()) {

                int id = rs.getInt("id");
                String name = rs.getString("name");
                boolean cap = rs.getBoolean("capital");
                double lat = rs.getDouble("latitude");
                double lon = rs.getDouble("longitude");
                int id_country = rs.getInt("id_country");
                System.out.println(id + "\t\t" + name + "\t\t" + cap + "\t\t" + lat + "\t\t" + lon + "\t\t" + id_country);
            }
        } catch (SQLException e) {

            LOGGER.warning("SQLException");
            e.printStackTrace();
        }
    }

    public double getLatitude(int id) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("select latitude from cities where id='" + id + "'")) {
            return rs.next() ? rs.getDouble(1) : null;
        }
    }

    public double getLongitude(int id) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("select longitude from cities where id='" + id + "'")) {
            return rs.next() ? rs.getDouble(1) : null;
        }
    }

    /**
     * @return - a list of all ids from table cities
     */
    public List<Integer> idCities() {
        List<Integer> ids = new ArrayList<>();
        Connection con = Database.getConnection();
        PreparedStatement p = null;
        ResultSet rs = null;
        try {
            String sql = "select id from cities";
            p = con.prepareStatement(sql);
            rs = p.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                ids.add(id);
            }
        } catch (SQLException e) {

            LOGGER.warning("SQLException");
            e.printStackTrace();
        }
        return ids;
    }
}
