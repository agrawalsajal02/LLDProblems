package meetingroom;

import java.util.List;

public interface RoomSelectionStrategy {
    List<RoomCandidate> orderCandidates(List<RoomCandidate> candidates);
}
