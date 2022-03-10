/**
 * @author ioana
 */

public class Main {

    public static void main(String[] args) {

        Problem p = new Problem();

        p.addEvent(new Event("C1", 100, 8, 10));
        p.addEvent(new Event("C2", 100, 10, 12));
        p.addEvent(new Event("L1", 30, 8, 10));
        p.addEvent(new Event("L2", 30, 8, 10));
        p.addEvent(new Event("L3", 30, 10, 12));

        p.addEvent(new Event("L3", 30, 10, 12)); // duplicate is not added

        p.addRoom(new ComputerLab("401", 30, "Windows"));
        p.addRoom(new ComputerLab("403", 30, "Linux"));
        p.addRoom(new ComputerLab("405", 30, "Windows"));
        p.addRoom(new LectureHall("309", 100, true));

        p.addRoom(new LectureHall("309", 100, true)); // duplicate is not added

        System.out.println(p);

        Schedule s = new Schedule(p);
        s.makeSchedule();
        s.printSchedule();
    }
}
