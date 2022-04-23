package DAO;

import database.Database;

import java.sql.*;
import java.util.logging.Logger;

public class ContinentDAO {
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * inserts a row into continent
     */
    public void create(String name) {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into public.continents (name) values (?)")) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            LOGGER.warning("sql exception in ContinentDAO, method create");
            System.err.println(e);
        }
    }

    /**
     * @return the id of the continent name specified
     */
    public Integer findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        Integer result = null;
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id from public.continents where name='" + name + "'")) {
            result = rs.next() ? rs.getInt(1) : null;
        } catch (SQLException e) {
            LOGGER.warning("sql exception in ContinentDAO, method findByName");
            System.err.println(e);
        }
        return result;
    }

    /**
     * @return the name of the continent with the id specified
     */
    public String findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        String result = null;
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select name from public.continents where id='" + id + "'")) {
            result = rs.next() ? rs.getString(1) : null;
        } catch (SQLException e) {
            LOGGER.warning("sql exception in ContinentDAO, method findByName");
            System.err.println(e);
        }
        return result;
    }
}
