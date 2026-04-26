package companiesProblem.uber.lld.meetingroom.meetingroomusinginstant;

import java.util.List;

public interface RoomSelectionStrategy {
    Room choose(List<Room> availableRooms);
}
