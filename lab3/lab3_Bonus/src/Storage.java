
public interface Storage {

    /**
     * getters and setters
     */
    int getStorageCapacity();
    void setStorageCapacity(int storageCapacity);

    /**
     * converts the capacity from GB to MB, KB or bytes
     */
    default int getStorageCapacityMB() {
        return getStorageCapacity()*1024;
    }

    default int getStorageCapacityKB() {
        return getStorageCapacity()*1024*1024;
    }

    default int getStorageCapacityBytes() {
        return getStorageCapacity()*1024*1024*1024;
    }
}
