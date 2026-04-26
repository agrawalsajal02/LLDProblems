package meetingroom;

public final class RoomCandidate {
    private final Room room;
    private final long spillageMinutes;

    public RoomCandidate(Room room, long spillageMinutes) {
        this.room = room;
        this.spillageMinutes = spillageMinutes;
    }

    public Room getRoom() {
        return room;
    }

    public long getSpillageMinutes() {
        return spillageMinutes;
    }
}
