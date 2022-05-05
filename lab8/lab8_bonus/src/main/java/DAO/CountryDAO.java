package DAO;

import database.Database;

import java.sql.*;
import java.util.logging.Logger;

/**
 * Data Access Object for countries table
 */
public class CountryDAO {
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * @param name      of the country
     * @param code      of the country
     * @param continent of the continent
     *                  insert into table countries this country
     */
    public void create(String name, String code, int continent) throws SQLException {
        if (findByName(name) == null) {
            try (Connection con = Database.getDataSource().getConnection(); PreparedStatement pstmt = con.prepareStatement("insert into countries (name,code,continent) values (?,?,?)")) {
                pstmt.setString(1, name);
                pstmt.setString(2, code);
                pstmt.setInt(3, continent);
                pstmt.executeUpdate();
            }
        }
    }

    /**
     * @param name - name of the country for witch I am searching the id
     * @return - id of the country with this name
     */
    public Integer findByName(String name) throws SQLException {
        try (Connection con = Database.getDataSource().getConnection(); Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("select id from countries where name='" + name + "'")) {
            return rs.next() ? rs.getInt(1) : null;
        }
    }

    /**
     * @param id - id of the country for witch I am searching the name
     * @return - name of the country with this id
     */
    public String findById(int id) throws SQLException {
        try (Connection con = Database.getDataSource().getConnection(); Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("select name from countries where id='" + id + "'")) {
            return rs.next() ? rs.getString(1) : null;
        }
    }

    /**
     * print all the countries from database
     */
    public void printCountries() throws SQLException {
        Connection con = Database.getDataSource().getConnection();
        PreparedStatement p = null;
        ResultSet rs = null;
        try {
            String sql = "select * from countries";
            p = con.prepareStatement(sql);
            rs = p.executeQuery();

            System.out.println("Table countries: ");
            System.out.println("id\t\tname\t\tcode\t\tcontinent");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String code = rs.getString("code");
                int continent = rs.getInt("continent");
                System.out.println(id + "\t\t" + name + "\t\t" + code + "\t\t" + continent);
            }
        } catch (SQLException e) {
            LOGGER.warning("SQLException");
            e.printStackTrace();
        }
        con.close();
    }

    /**
     * prints all the countries from the continent with id = idc
     */
    public void printCountriesFromContinent(int idc) throws SQLException {
        System.out.println("The countries are: ");
        try (Connection con = Database.getDataSource().getConnection(); Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("select name from countries where idc='" + idc + "'")) {
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
        }
    }
}
