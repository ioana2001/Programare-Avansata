package DAO;

import database.Database;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.sql.*;

/**
 * Data Access Object for sisters table
 */
public class SistersDAO {
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * inserts into sisters table a new row with given parameters
     */
    public void create(int first_id, int second_id) throws SQLException {

        if (findById(first_id) != second_id) {
            try (Connection con = Database.getDataSource().getConnection(); PreparedStatement pstmt = con.prepareStatement("insert into sisters (first_id,second_id) values (?,?)")) {
                pstmt.setInt(1, first_id);
                pstmt.setInt(2, second_id);
                pstmt.executeUpdate();
            }
            try (Connection con = Database.getDataSource().getConnection(); PreparedStatement pstmt = con.prepareStatement("insert into sisters (first_id,second_id) values (?,?)")) {
                pstmt.setInt(1, second_id);
                pstmt.setInt(2, first_id);
                pstmt.executeUpdate();
            }
        }
    }

    /**
     * @return - the id of the sister of given id
     */
    public Integer findById(int id) throws SQLException {
        try (Connection con = Database.getDataSource().getConnection(); Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("select * from sisters where first_id='" + id + "'")) {
            return rs.next() ? rs.getInt(1) : 0;
        }
    }

    /**
     * @return - a list of all the sisters ids of given id
     */
    public List<Integer> findAllById(int id) throws SQLException {
        List<Integer> ids = new ArrayList<>();
        Connection con = null;
        con = Database.getDataSource().getConnection();
        PreparedStatement p = null;
        ResultSet rs = null;
        try {
            String sql = "select second_id from sisters where first_id='" + id + "'";
            p = con.prepareStatement(sql);
            rs = p.executeQuery();
            while (rs.next()) {
                ids.add(rs.getInt("second_id"));
            }
        } catch (SQLException e) {

            LOGGER.warning("SQLException");
            e.printStackTrace();
        }
        con.close();
        return ids;
    }


    public void printSisters() throws SQLException {
        Connection con = Database.getDataSource().getConnection();
        PreparedStatement p = null;
        ResultSet rs = null;
        try {
            String sql = "select * from sisters";
            p = con.prepareStatement(sql);
            rs = p.executeQuery();

            System.out.println("Table sisters: ");
            System.out.println("first_id\t\tsecond_id");
            while (rs.next()) {
                int first_id = rs.getInt("first_id");
                int second_id = rs.getInt("second_id");
                System.out.println(first_id + "\t\t" + second_id);
            }
        } catch (SQLException e) {
            LOGGER.warning("SQLException");
            e.printStackTrace();
        }
        con.close();
    }
}
