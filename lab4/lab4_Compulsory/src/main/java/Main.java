
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author ioana
 */

public class Main {

    public static void main(String[] args) {

        int[] streetLengths = {1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3};

        Stream<String> intersectionNames = IntStream.rangeClosed(0, 8)
                .mapToObj(i -> new String("Intersection " + (i + 1)));

        Set<Intersection> intersections = intersectionNames
                .map(Intersection::new).collect(Collectors.toSet());

        Intersection[] intersectionArray = intersections.toArray(Intersection[]::new);

        Street[] streetArray = IntStream.rangeClosed(0, 15)
                .mapToObj(i -> new Street("Street " + (i + 1), streetLengths[i]))
                .toArray(Street[]::new);

        Stream<Street> streets = Arrays.stream(streetArray)
                .sorted(Comparator.comparing(Street::getLength))
                .collect(Collectors.toCollection(LinkedList::new))
                .stream();

        streetArray[0].setIntersections(intersectionArray[2], intersectionArray[3]);
        streetArray[1].setIntersections(intersectionArray[6], intersectionArray[8]);
        streetArray[2].setIntersections(intersectionArray[6], intersectionArray[7]);
        streetArray[3].setIntersections(intersectionArray[7], intersectionArray[8]);
        streetArray[4].setIntersections(intersectionArray[5], intersectionArray[7]);
        streetArray[5].setIntersections(intersectionArray[4], intersectionArray[5]);
        streetArray[6].setIntersections(intersectionArray[0], intersectionArray[3]);
        streetArray[7].setIntersections(intersectionArray[0], intersectionArray[2]);
        streetArray[8].setIntersections(intersectionArray[1], intersectionArray[3]);
        streetArray[9].setIntersections(intersectionArray[1], intersectionArray[2]);
        streetArray[10].setIntersections(intersectionArray[3], intersectionArray[6]);
        streetArray[11].setIntersections(intersectionArray[3], intersectionArray[4]);
        streetArray[12].setIntersections(intersectionArray[5], intersectionArray[8]);
        streetArray[13].setIntersections(intersectionArray[0], intersectionArray[5]);
        streetArray[14].setIntersections(intersectionArray[2], intersectionArray[4]);
        streetArray[15].setIntersections(intersectionArray[4], intersectionArray[8]);

        streets.forEach(System.out::println);
        intersections.forEach(System.out::println);
    }
}














