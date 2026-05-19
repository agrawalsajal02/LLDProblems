package companiesProblem.uber.lld.meetingroomSample;

import meetingroomSample.RoomCandidate;

import java.util.List;

public interface RoomSelectionStrategy {
    List<meetingroomSample.RoomCandidate> orderCandidates(List<RoomCandidate> candidates);
}
