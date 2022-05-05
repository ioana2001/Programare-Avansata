package map;

import DAO.CitiesDAO;

import java.sql.SQLException;

/**
 * class for calculating the distance between two cities
 */
public class Distance {
    public static double distance(double lat1, double lon1, double lat2, double lon2) {

        // The math module contains a function
        // named toRadians which converts from
        // degrees to radians.
        lon1 = Math.toRadians(lon1);
        lon2 = Math.toRadians(lon2);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        // Haversine formula
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2), 2);
        double c = 2 * Math.asin(Math.sqrt(a));

        // Radius of earth in kilometers. Use 3956
        // for miles
        double r = 6371;

        // calculate the result
        return (c * r);
    }

    public static void printDistance(int id1, int id2) throws SQLException {
        var cities = new CitiesDAO();
        System.out.println("Distance between " + cities.findById(id1) + " and " + cities.findById(id2) + " is " + Distance.distance(cities.getLatitude(id1), cities.getLongitude(id1), cities.getLatitude(id2), cities.getLongitude(id2)) + " KM");

    }
}
