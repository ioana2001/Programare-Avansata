
public class ComputerLab extends Room {
    String operatingSystem;

    public ComputerLab(String name, int size, String operatingSystem) {
        super(name, size);
        this.operatingSystem = operatingSystem;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    @Override
    public String toString() {
        return this.getName() + "(cap=" + this.getSize() + ", lab, " + operatingSystem + ")";
    }
}
