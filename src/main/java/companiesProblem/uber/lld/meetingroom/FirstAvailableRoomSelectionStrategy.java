package companiesProblem.uber.lld.meetingroom;

import java.util.List;

public final class FirstAvailableRoomSelectionStrategy implements RoomSelectionStrategy {
    @Override
    public Room choose(List<Room> availableRooms) {
        return availableRooms.isEmpty() ? null : availableRooms.get(0);
    }
}
