package DAO;

import database.Database;

import java.sql.*;
import java.util.logging.Logger;

/**
 * DAO for table countries from the database
 */
public class CountryDAO {
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * @param name of the country
     * @param code of the country
     * @param continent  of the continent
     *             insert into table countries this country
     */
    public void create(String name, String code, int continent) throws SQLException {
        Connection con = Database.getConnection();
        if (findByName(name) == null) {
            try (PreparedStatement pstmt = con.prepareStatement("insert into countries (name,code,continent) values (?,?,?)")) {
                pstmt.setString(1, name);
                pstmt.setString(2, code);
                pstmt.setInt(3, continent);
                pstmt.executeUpdate();
                con.commit();
            }
        }
    }

    /**
     * @param name - name of the country for witch I am searching the id
     * @return -id of the country with this name
     */
    public Integer findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("select id from countries where name='" + name + "'")) {
            return rs.next() ? rs.getInt(1) : null;
        }
    }

    /**
     * @param id -id of the country for witch I am searching the name
     * @return -name of the country with this id
     */
    public String findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("select name from countries where id='" + id + "'")) {
            return rs.next() ? rs.getString(1) : null;
        }
    }

    /**
     * print all the countries from database
     */
    public void printCountries() {
        Connection con = Database.getConnection();
        PreparedStatement p = null;
        ResultSet rs = null;
        try {
            String sql = "select * from countries";
            p = con.prepareStatement(sql);
            rs = p.executeQuery();

            System.out.println("Table countries: ");
            System.out.println("id\t\tname\t\tcode\t\tidc");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String code = rs.getString("code");
                int idc = rs.getInt("idc");
                System.out.println(id + "\t\t" + name + "\t\t" + code + "\t\t" + idc);
            }
        } catch (SQLException e) {
            LOGGER.warning("SQLException");
            e.printStackTrace();
        }
    }

    /**
     * prints all the countries from the continent with id=idc
     */
    public void printCountriesFromContinent(int idc) throws SQLException {
        Connection con = Database.getConnection();
        System.out.println("The countries are: ");
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("select name from countries where idc='" + idc + "'")) {
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
        }
    }
}
