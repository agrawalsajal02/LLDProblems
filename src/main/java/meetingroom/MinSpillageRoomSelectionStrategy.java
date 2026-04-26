package meetingroom;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class MinSpillageRoomSelectionStrategy implements RoomSelectionStrategy {
    @Override
    public List<RoomCandidate> orderCandidates(List<RoomCandidate> candidates) {
        List<RoomCandidate> ordered = new ArrayList<>(candidates);
        ordered.sort(
            Comparator
                .comparingLong(RoomCandidate::getSpillageMinutes)
                .thenComparingInt(candidate -> candidate.getRoom().getCapacity())
                .thenComparing(candidate -> candidate.getRoom().getRoomId())
        );
        return ordered;
    }
}
