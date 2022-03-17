
public interface Identifiable {
    /**
     * getters and setters (if a class is identifiable it must have an address and a name)
     */
    String getAddress();
    void setAddress(String address);
    String getName();
    void setName(String name);
}
