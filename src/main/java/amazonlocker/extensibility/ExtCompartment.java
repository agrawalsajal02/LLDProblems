package amazonlocker.extensibility;

import amazonlocker.Size;

public class ExtCompartment {
    private final String id;
    private final Size size;
    private CompartmentStatus status;

    public ExtCompartment(String id, Size size) {
        this.id = id;
        this.size = size;
        this.status = CompartmentStatus.AVAILABLE;
    }

    public String getId() {
        return id;
    }

    public Size getSize() {
        return size;
    }

    public boolean isAvailable() {
        return status == CompartmentStatus.AVAILABLE;
    }

    public void markOccupied() {
        status = CompartmentStatus.OCCUPIED;
    }

    public void markAvailable() {
        status = CompartmentStatus.AVAILABLE;
    }

    public void markOutOfService() {
        status = CompartmentStatus.OUT_OF_SERVICE;
    }

    public void markReserved() {
        status = CompartmentStatus.RESERVED;
    }

    public void open() {
        // Hardware unlock in real system
    }
}
