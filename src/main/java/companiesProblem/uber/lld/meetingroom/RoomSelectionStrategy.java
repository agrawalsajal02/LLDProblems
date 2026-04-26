package companiesProblem.uber.lld.meetingroom;

import java.util.List;

public interface RoomSelectionStrategy {
    Room choose(List<Room> availableRooms);
}
