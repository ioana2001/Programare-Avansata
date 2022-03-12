public class Computer extends Node implements Identifiable, Storage {
    int storageCapacity;
    String ipAddress;

    public Computer(String name, String location, String macAddress, String ipAddress, int storageCapacity) {
        super(name, location, macAddress);
        this.storageCapacity = storageCapacity;
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

    public int getStorageCapacity() {
        return storageCapacity;
    }

    public void setStorageCapacity(int storageCapacity) {
        this.storageCapacity = storageCapacity;
    }

    @Override
    public String toString() {
        return name + "(location: " + location + ",IP Address: " + ipAddress + ",MAC Address: " + macAddress + ",Capacity: " + storageCapacity + ")";
    }
}
