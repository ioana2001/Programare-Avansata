
public class Schedule {

    Problem p;
    boolean[][] schedule;
    int nrRooms;
    int nrEvents;

    /**
     * Constructor
     * @param p : a problem instance
     */
    public Schedule(Problem p) {
        this.p = p;
        nrRooms = p.getRooms().size();
        nrEvents = p.getEvents().size();
        schedule = new boolean[nrRooms][nrEvents];
        for (int i = 0; i < nrRooms; i++)
            for (int j = 0; j < nrEvents; j++)
                schedule[i][j] = false;
    }

    /**
     * makes the Schedule putting the biggest event in the biggest room that is free in the event's interval
     */
    void makeSchedule() {
        p.sortRoomCapacity();
        p.sortEventCapacity();

        for (int i = nrEvents - 1; i >= 0; i--) {
            for (int j = nrRooms - 1; j >= 0; j--) {
                if (checkEventFit(i, j)) {
                    schedule[j][i] = true;
                    break;
                }
            }
        }

    }

    /**
     * checks if the indexEvent-th event fits in the indexRoom-th room and return true if it fits and false otherwise
     */
    boolean checkEventFit(int indexEvent, int indexRoom) {
        int startEventTime = p.getEvents().get(indexEvent).getStart();
        int endEventTime = p.getEvents().get(indexEvent).getEnd();
        for (int i = 0; i < nrEvents; i++)
            if (schedule[indexRoom][i]) {
                int iStart = p.getEvents().get(i).getStart();
                int iEnd = p.getEvents().get(i).getEnd();
                if ((startEventTime > iStart && startEventTime < iEnd) ||
                        (endEventTime > iStart && endEventTime < iEnd) ||
                        (startEventTime <= iStart && endEventTime >= iEnd))
                    return false;
            }
        return true;
    }

    public void printSchedule() {
        for (int i = 0; i < nrRooms; i++) {
            for (int j = 0; j < nrEvents; j++)
                if (schedule[i][j]) {
                    System.out.println(p.getRooms().get(i).getName() + " -> " + p.getEvents().get(j).getName());
                }
        }
    }
}
