import java.sql.Time;

/**
 * @author ioana
 */

public class Main {

    public static void main(String[] args) {

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
    }
}
