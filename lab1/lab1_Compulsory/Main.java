
/**
 * @author Ioana Petrariu
 */

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        String[] languages = {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};

        // generarea numarului n
        int n = (int) (Math.random() * 1_000_000);
        n *= 3;
        n += Integer.parseInt("10101", 2);
        n += Integer.parseInt("FF", 16);
        n *= 6;

        int result = getFinalDigitSum(n);

        System.out.println("Willy-nilly, this semester I will learn " + languages[result]);
    }

    /**
     * suma cifrelor unui numar pana ajungem la o singura cifra
     */
    public static int getFinalDigitSum(int x) {
        while (x > 9) {
            x = getDigitSum(x);
        }
        return x;
    }

    /**
     * suma cifrelor unui numar
     */
    public static int getDigitSum(int x) {
        int sum = 0;
        while (x > 0) {
            sum += x % 10;
            x /= 10;
        }
        return sum;
    }

}
