/**
 * @author ioana
 */
public class Main {

    public static void main(String[] args) {

        Event e1 = new Event("C1", 100, 8, 10);
        Event e2 = new Event("C2", 100, 10, 12);
        Event e3 = new Event("L1", 30, 8, 10);
        Event e4 = new Event("L2", 30, 8, 10);
        Event e5 = new Event("L3", 30, 10, 12);

        Room r1 = new Room("401", 30, RoomType.COMPUTER_LAB);
        Room r2 = new Room("403", 30, RoomType.COMPUTER_LAB);
        Room r3 = new Room("405", 30, RoomType.COMPUTER_LAB);
        Room r4 = new Room("309", 100, RoomType.LECTURE_HALL);

        System.out.println(e1.toString());
        System.out.println(e2.toString());
        System.out.println(e3.toString());
        System.out.println(e4.toString());
        System.out.println(e5.toString());
        System.out.println();
        System.out.println(r1.toString());
        System.out.println(r2.toString());
        System.out.println(r3.toString());
        System.out.println(r4.toString());

    }
}
