
public class LectureHall extends Room {
    boolean videoProjector;

    public LectureHall(String name, int size, boolean videoProjector) {
        super(name, size);
        this.videoProjector = videoProjector;
    }

    public boolean isVideoProjector() {
        return videoProjector;
    }

    public void setVideoProjector(boolean videoProjector) {
        this.videoProjector = videoProjector;
    }

    @Override
    public String toString() {
        String isVideoProjector = ", videoProjector";
        if (!videoProjector)
            isVideoProjector = "";
        return this.getName() + "(size=" + this.getSize() + ", lecture hall" + isVideoProjector + ")";
    }
}
