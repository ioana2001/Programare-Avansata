package DAO;

import database.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Data Access Object for cities table
 */
public class CitiesDAO {
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * Inserts into table cities a new row
     */
    public void create(String name, boolean capital, double latitude, double longitude, int id_country) throws SQLException {
        if (findByName(name) == null) {
            try (Connection con = Database.getDataSource().getConnection(); PreparedStatement pstmt = con.prepareStatement("insert into cities (name,capital,latitude,longitude,id_country) values (?,?,?,?,?)")) {
                pstmt.setString(1, name);
                pstmt.setBoolean(2, capital);
                pstmt.setDouble(3, latitude);
                pstmt.setDouble(4, longitude);
                pstmt.setInt(5, id_country);
                pstmt.executeUpdate();
            }
        }
    }

    /**
     * @return - the id of the city with the given name
     */
    public Integer findByName(String name) throws SQLException {
        try (Connection con = Database.getDataSource().getConnection(); Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("select id from cities where name='" + name + "'")) {
            return rs.next() ? rs.getInt(1) : null;
        }
    }

    /**
     * @return - the name of the city with the given id
     */
    public String findById(int id) {
        try (Connection con = Database.getDataSource().getConnection(); Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("select name from cities where id='" + id + "'")) {
            return rs.next() ? rs.getString(1) : null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void printCities() throws SQLException {
        Connection con = Database.getDataSource().getConnection();
        PreparedStatement p = null;
        ResultSet rs = null;
        try {

            String sql = "select * from cities";
            p = con.prepareStatement(sql);
            rs = p.executeQuery();

            System.out.println("Table cities: ");
            System.out.println("id\t\tname\t\tcapital\t\tlatitude\t\tlongitude\t\tid_country");
            while (rs.next()) {

                int id = rs.getInt("id");
                String name = rs.getString("name");
                boolean cap = rs.getBoolean("capital");
                double lat = rs.getDouble("latitude");
                double lon = rs.getDouble("longitude");
                int id_country = rs.getInt("id_country");
                System.out.println(id + "\t\t" + name + "\t\t" + cap + "\t\t" + lat + "\t\t\t\t" + lon + "\t\t\t\t" + id_country);
            }
        } catch (SQLException e) {

            LOGGER.warning("SQLException");
            e.printStackTrace();
        }
        con.close();
    }

    public double getLatitude(int id) throws SQLException {
        try (Connection con = Database.getDataSource().getConnection(); Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("select latitude from cities where id='" + id + "'")) {
            return rs.next() ? rs.getDouble(1) : null;
        }
    }

    public double getLongitude(int id) throws SQLException {
        try (Connection con = Database.getDataSource().getConnection(); Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("select longitude from cities where id='" + id + "'")) {
            return rs.next() ? rs.getDouble(1) : null;
        }
    }

    /**
     * @return - a list with all the ids of the cities table
     */
    public List<Integer> idCities() throws SQLException {
        List<Integer> ids = new ArrayList<>();
        Connection con = null;
        con = Database.getDataSource().getConnection();
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
        con.close();
        return ids;
    }
}
