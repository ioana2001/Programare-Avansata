package DAO;

import database.Database;

import java.sql.*;
import java.util.logging.Logger;

public class CountryDAO {
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * inserts a row with the name specified and the continents id
     */
    public void create(String name, int id) {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into countries (name, continent, code) values (?, ?, ?)")) {
            pstmt.setString(1, name);
            pstmt.setInt(2, id);
            pstmt.setString(3, "1");
            pstmt.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            LOGGER.warning("sql exception in CountryDAO, method create");
            System.err.println(e);
        }
    }

    /**
     * @return the id of the country name specified
     */
    public Integer findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        Integer result = null;
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id from public.countries where name='" + name + "'")) {
            result = rs.next() ? rs.getInt(1) : null;
        } catch (SQLException e) {
            LOGGER.warning("sql exception in ContinentDAO, method findByName");
            System.err.println(e);
        }
        return result;
    }

    /**
     * @return the name of the country with the id specified
     */
    public String findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        String result = null;
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select name from public.countries where id='" + id + "'")) {
            result = rs.next() ? rs.getString(1) : null;
        } catch (SQLException e) {
            LOGGER.warning("sql exception in ContinentDAO, method findByName");
            System.err.println(e);
        }
        return result;
    }

    /**
     * prints all the countries from the continent specified by its id
     */
    public void printCountriesFromContinent(int continent) {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select name from public.countries where continent=" + continent + ";")) {
            while(rs.next()) {
                System.out.println(rs.getString("name"));
            }
        } catch (SQLException e) {
            LOGGER.warning("sql exception in CountryDAO, method printCountriesFromContinent");
            System.err.println(e);
        }
    }

}
