package companiesProblem.uber.lld.meetingroomSample;

import meetingroomSample.Room;

public final class RoomCandidate {
    private final meetingroomSample.Room room;
    private final long spillageMinutes;

    public RoomCandidate(meetingroomSample.Room room, long spillageMinutes) {
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
