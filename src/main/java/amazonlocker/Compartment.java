package amazonlocker;

public class Compartment {
    private final String id;
    private final Size size;
    private boolean occupied;

    public Compartment(String id, Size size) {
        this.id = id;
        this.size = size;
        this.occupied = false;
    }

    public String getId() {
        return id;
    }

    public Size getSize() {
        return size;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void markOccupied() {
        this.occupied = true;
    }

    public void markFree() {
        this.occupied = false;
    }

    public void open() {
        // Hardware unlock in real system
    }
}
