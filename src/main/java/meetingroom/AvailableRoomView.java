package meetingroom;

public final class AvailableRoomView {
    private final String roomId;
    private final int capacity;
    private final long spillageMinutes;

    public AvailableRoomView(String roomId, int capacity, long spillageMinutes) {
        this.roomId = roomId;
        this.capacity = capacity;
        this.spillageMinutes = spillageMinutes;
    }

    public String getRoomId() {
        return roomId;
    }

    public int getCapacity() {
        return capacity;
    }

    public long getSpillageMinutes() {
        return spillageMinutes;
    }

    @Override
    public String toString() {
        return "AvailableRoomView{"
            + "roomId='" + roomId + '\''
            + ", capacity=" + capacity
            + ", spillageMinutes=" + spillageMinutes
            + '}';
    }
}
