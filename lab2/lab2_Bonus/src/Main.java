import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * @author ioana
 */

public class Main {

    public static void main(String[] args) throws InterruptedException{

        Problem p = new Problem();

        p.addEvent(new Event("C1", 100, new Time(8,0,0), new Time(10, 0, 0)));
        p.addEvent(new Event("C2", 100, new Time(10, 0, 0), new Time(12,0,0)));
        p.addEvent(new Event("L1", 30, new Time(8,0,0), new Time(10, 0, 0)));
        p.addEvent(new Event("L2", 30, new Time(8,0,0), new Time(10, 0, 0)));
        p.addEvent(new Event("L3", 30, new Time(10, 0, 0), new Time(12,0,0)));

        p.addRoom(new ComputerLab("401", 30, "Windows"));
        p.addRoom(new ComputerLab("403", 30, "Linux"));
        p.addRoom(new ComputerLab("405", 30, "Windows"));
        p.addRoom(new LectureHall("309", 100, true));

        System.out.println(p);

        // Greedy
        System.out.println("\nWith Greedy algorithm:");
        Schedule s = new Schedule(p);
        s.makeSchedule();
        s.printSchedule();

        // DSatur
        System.out.println("\nWith DSatur algorithm:");
        SolutionDSatur d = new SolutionDSatur(p);
        d.colorDSatur();
        d.printSolution();

        System.out.println("------------------");

        /**
         * Test for bigger instances
         */
        testingTime(3000, 750, 750);

        System.out.println();
    }

    /**
     * takes as input the number for events and rooms(computer Labs and Lecture Halls)
     * and generates random values for them to test the time of each algorithm
     */
    public static void testingTime(int nrEvents, int nrComputerLab, int nrLectureHall) throws InterruptedException{
        Problem p = new Problem();
        for(int i = 0; i < nrEvents; i++)
            p.addEvent(new Event());
        for(int i = 0; i < nrComputerLab; i++)
            p.addRoom(new ComputerLab());
        for(int i = 0; i < nrLectureHall; i++)
            p.addRoom(new LectureHall());

        // Greedy
        // start measuring time
        long startTime = System.nanoTime();

        System.out.println("\nWith Greedy algorithm:");
        Schedule s = new Schedule(p);
        s.makeSchedule();

        // finish measuring time
        TimeUnit.SECONDS.sleep(1);
        long endTime = System.nanoTime();
        long timeElapsed = endTime - startTime;

        System.out.println("Execution Time in nanosecunde: " + timeElapsed);

        // DSatur
        // start measuring time
        startTime = System.nanoTime();

        System.out.println("\nWith DSatur algorithm:");
        SolutionDSatur d = new SolutionDSatur(p);
        d.colorDSatur();

        // finish measuring time
        TimeUnit.SECONDS.sleep(1);
        endTime = System.nanoTime();
        timeElapsed = endTime - startTime;

        System.out.println("Execution Time in nanosecunde: " + timeElapsed);
    }

}
