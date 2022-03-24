
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author ioana
 */

public class Main {

    public static void main(String[] args) {

        Street[] streetArray = IntStream.rangeClosed(0, 15)
                .mapToObj(i -> new Street(RandomGenerator.generateStreetName(), RandomGenerator.generateLength()))
                .toArray(Street[]::new);

        Stream<Street> streets = Arrays.stream(streetArray)
                .sorted(Comparator.comparing(Street::getLength))
                .collect(Collectors.toCollection(LinkedList::new))
                .stream();

        Stream<String> intersectionNames = IntStream.rangeClosed(0, 8)
                .mapToObj(i -> RandomGenerator.generateIntersectionName());

        Stream<Intersection> intersections = intersectionNames.map(Intersection::new);

        Intersection[] intersectionArray = intersections.toArray(Intersection[]::new);

        addStreets(intersectionArray, streetArray);

        // prints the streets and intersections
        streets.forEach(System.out::println);
        for (Intersection i : intersectionArray)
            System.out.println(i);

        City c = new City(intersectionArray);

        // streets with length greater than val and joins ar least 3 streets
        int val = 50;
        System.out.println("\nstreets with length greater than " + val + " and joins ar least 3 streets:");
        Stream<Street> streetsSpecial = Arrays.stream(streetArray)
                .filter(l -> l.getLength() > val)
                .filter(l -> c.getNumberOfAdjacentStreets(l) >= 3)
                .collect(Collectors.toCollection(LinkedList::new))
                .stream();
        streetsSpecial.forEach(System.out::println);
        System.out.println();

        PrimMST mst = new PrimMST(c);
        mst.primMST();

        City randomCity = new City(5);
        PrimMST randomCityMST = new PrimMST(randomCity);

        System.out.println("\nMinimum route for the random generated city: " + randomCity.getMinimumRoute());
    }

    private static void addStreets(Intersection[] intersectionArray, Street[] streetArray) {
        intersectionArray[0].addNeighbor(intersectionArray[1], streetArray[0]);
        intersectionArray[1].addNeighbor(intersectionArray[2], streetArray[4]);
        intersectionArray[2].addNeighbor(intersectionArray[0], streetArray[3]);
        intersectionArray[1].addNeighbor(intersectionArray[5], streetArray[6]);
        intersectionArray[0].addNeighbor(intersectionArray[4], streetArray[8]);
        intersectionArray[1].addNeighbor(intersectionArray[7], streetArray[2]);
        intersectionArray[3].addNeighbor(intersectionArray[4], streetArray[1]);
        intersectionArray[4].addNeighbor(intersectionArray[5], streetArray[5]);
        intersectionArray[5].addNeighbor(intersectionArray[2], streetArray[7]);
        intersectionArray[6].addNeighbor(intersectionArray[1], streetArray[9]);
        intersectionArray[7].addNeighbor(intersectionArray[8], streetArray[10]);
        intersectionArray[8].addNeighbor(intersectionArray[5], streetArray[11]);
        intersectionArray[3].addNeighbor(intersectionArray[2], streetArray[12]);
        intersectionArray[4].addNeighbor(intersectionArray[6], streetArray[13]);
    }

}














