
public class Switch extends Node{

    /**
     * constructor
     */
    public Switch(String name, String location, String macAddress, String ipAddress) {
        super(name, location, macAddress);
    }

    /**
     * getter and setter for name
     */
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name + "(location: " + location + ", MAC Address: " + macAddress + ")";
    }
}
