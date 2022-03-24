import com.github.javafaker.Faker;

import java.util.Random;

public class RandomGenerator {

    static String generateStreetName() {
        Faker name = new Faker();
        return "Street " + name.address().streetName();
    }

    static String generateIntersectionName() {
        Faker name = new Faker();
        return "Intersection " + name.address().streetName();
    }

    static int generateLength() {
        Random rand = new Random();
        return rand.nextInt(100);
    }

}
