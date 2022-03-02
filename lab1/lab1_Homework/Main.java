
import java.util.Scanner;
import java.util.ArrayList;

import java.util.concurrent.TimeUnit;

/**
 * tema 1 + Bonus - PA
 *
 * @author ioana
 */

public class Main {

    public static void main(String[] args) throws InterruptedException {

        int n, p;
        String letters;
        char[] alphabet = new char[26];

        if (args.length == 0) {
            Scanner scanner = new Scanner(System.in);
            n = scanner.nextInt();
            p = scanner.nextInt();
            letters = scanner.next();
            alphabet = letters.toCharArray();
        } else {
            n = Integer.parseInt(args[0]);
            p = Integer.parseInt(args[0]);
            for (int i = 2; i < args.length; i++)
                alphabet[i - 2] = args[i].charAt(0);
        }

        // tema
        printWordsStructure(n, p, alphabet);

        // masurarea in nanosecunde
        // measureRunningTime(n, p, alphabet);

         // Bonus
         // String[] words = createWordsArray(n, p, alphabet);
         // printStringArray(words, n);
         // System.out.println(findMaxNeighborsLength(n, words));

    }

    /**
     * Bonus: gasirea celei mai lungi secvente de vecini
     * complexitate: O(n^2)
     */
    public static int findMaxNeighborsLength (int n, String[] words) {
        boolean[][] neighbors = createNeighbors(n, words);

        int maxLength = 0;
        int[] neighborsGroups = new int[n];
        int neighborsGroupsLength = 0;
        int currentLength = 0;
        for (int i = 0; i < n - 1; i++) {
            if (neighbors[i][i + 1])
                currentLength++;
            else {
                if (currentLength > maxLength)
                    maxLength = currentLength;
                neighborsGroups[neighborsGroupsLength++] = currentLength;
                currentLength = 1;
            }
        }
        if (currentLength > maxLength)
            maxLength = currentLength;

        if(maxLength < neighborsGroups[0] + neighborsGroups[neighborsGroupsLength - 1] && neighbors[0][neighborsGroupsLength - 1])
            maxLength = neighborsGroups[0] + neighborsGroups[neighborsGroupsLength - 1];

        return maxLength;
    }

    /**
     * genereaza un String[] care contine n cuvinte generate a cate p litere, dupa care creaza o matrice neighbors
     * (neighbors[i][j] == true, daca words[i] este vecin cu words[j] si neighbors[i][j] == false, altfel)
     *
     * @param n        : numarul de cuvinte care trebuie generat
     * @param p        : numarul de litere al cuvintelor generate
     * @param alphabet : literele folosite pentru generarea cuvintelor
     */
    public static void printWordsStructure(int n, int p, char[] alphabet) {
        String[] words = createWordsArray(n, p, alphabet);
        printStringArray(words, n);
        boolean[][] neighbors = createNeighbors(n, words);
        printBooleanMatrix(neighbors, n);

        ArrayList<ArrayList<String>> listNeighbors = createNeighborsList(n, neighbors, words);
        printNeighborsList(n, listNeighbors, words);
    }

    /**
     * masoara timpul (in nanosecunde) de generare a n cuvinte a cate p litere
     *
     * @param n        : numarul de cuvinte care trebuie generat
     * @param p        : numarul de litere al cuvintelor generate
     * @param alphabet : literele folosite pentru generarea cuvintelor
     */
    public static void measureRunningTime(int n, int p, char[] alphabet) throws InterruptedException {

        long startTime = System.nanoTime();
        // incepe masurarea timpului de executie a codului

        String[] words = createWordsArray(n, p, alphabet);
        boolean[][] neighbors = createNeighbors(n, words);
        String[][] neighborsList = new String[n][n];

        TimeUnit.SECONDS.sleep(5);

        // masurearea timpului de executie a codului s-a terminat
        long endTime = System.nanoTime();

        long timeElapsed = endTime - startTime;
        System.out.println("Timpul de executie in nanosecunde: " + timeElapsed);
    }

    /**
     * functia este folosita pentru afisarea structurii de date care retine vecinii fiecarui cuvant
     *
     * @param n             : nr de vecini
     * @param listNeighbors : structura de date pentru vecinii fiecarui cuvant
     * @param words         : cuvintele folosite pentru lista de vecini
     */
    public static void printNeighborsList(int n, ArrayList<ArrayList<String>> listNeighbors, String[] words) {
        System.out.println();
        for (int i = 0; i < n; i++) {
            System.out.print(words[i] + ": ");
            ArrayList<String> listNeighborsItem = listNeighbors.get(i);
            for (String s : listNeighborsItem) System.out.print(s + " ");
            System.out.println();
        }
    }

    /**
     * construieste structura de date care contine toate cuvintele impreuna cu vecinii lor
     *
     * @param n         : nr de cuvinte
     * @param neighbors : matricea care are neighbors[i][j] == true daca words[i] si words[j] sunt vecini
     * @param words     : contine cuvintele pentru care se generaza lista de vecini
     * @return : structura care contine vecinii fiecarui cuvant
     */
    public static ArrayList<ArrayList<String>> createNeighborsList(int n, boolean[][] neighbors, String[] words) {
        ArrayList<ArrayList<String>> listNeighbors = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            ArrayList<String> listNeighborsItem = new ArrayList<>(n);
            for (int j = 0; j < n; j++)
                if (neighbors[i][j])
                    listNeighborsItem.add(words[j]);
            listNeighbors.add(listNeighborsItem);
        }
        return listNeighbors;
    }

    /**
     * formeaza matricea de cuvinte
     *
     * @param n        : nr de cuvinte generate
     * @param p        : nr de litere pentru fiecare cuvant
     * @param alphabet : alfabetul folosit pentru generarea cuvintelor
     * @return : un String[] care contine cuvintele generate
     */
    public static String[] createWordsArray(int n, int p, char[] alphabet) {
        String[] words = new String[n];
        for (int i = 0; i < n; i++) {
            String word = "";
            for (int j = 0; j < p; j++) {
                word = word.concat(Character.toString(alphabet[(int) (Math.random() * (alphabet.length))]));
            }
            words[i] = word;
        }
        return words;
    }

    /**
     * afiseaza matricea matrix[][] de dimensiune n*n
     */
    public static void printBooleanMatrix(boolean[][] matrix, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                System.out.print(matrix[i][j] + " ");
            System.out.println();
        }
    }

    public static void printStringArray(String[] array, int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    /**
     * contruieste si returneaza matricea care are relatia de vecinatate dintre cuvinte
     */
    public static boolean[][] createNeighbors(int n, String[] words) {
        boolean[][] neighbors = new boolean[n][n];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (verifyCommonChar(words[i], words[j]))
                    neighbors[i][j] = true;
        return neighbors;
    }

    /**
     * returneaza true daca s1 si s2 au un caracter in comun si false in caz contrar
     */
    public static boolean verifyCommonChar(String s1, String s2) {
        for (int i = 0; i < s1.length(); i++)
            for (int j = 0; j < s2.length(); j++)
                if (s1.charAt(i) == s2.charAt(j))
                    return true;
        return false;
    }
}
