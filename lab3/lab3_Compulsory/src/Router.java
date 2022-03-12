
public class Router extends Node implements Identifiable {
    String ipAddress;

    public Router(String name, String location, String macAddress, String ipAddress) {
        super(name, location, macAddress);
        this.ipAddress = ipAddress;
    }

    @Override
    public String getAddress() {
        return ipAddress;
    }

    @Override
    public void setAddress(String address) {
        this.ipAddress = address;
    }

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
        return name + "(location: " + location + ",IP Address: " + ipAddress + ",MAC Address: " + macAddress + ")";
    }
}
