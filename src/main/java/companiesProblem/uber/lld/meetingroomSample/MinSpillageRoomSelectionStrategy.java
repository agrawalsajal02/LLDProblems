//package companiesProblem.uber.lld.meetingroomSample;
//
//
//import java.util.ArrayList;
//import java.util.Comparator;
//import java.util.List;
//
//public final class MinSpillageRoomSelectionStrategy implements RoomSelectionStrategy {
//    @Override
//    public List<meetingroomSample.RoomCandidate> orderCandidates(List<meetingroomSample.RoomCandidate> candidates) {
//        List<meetingroomSample.RoomCandidate> ordered = new ArrayList<>(candidates);
//        ordered.sort(
//            Comparator
//                .comparingLong(RoomCandidate::getSpillageMinutes)
//                .thenComparingInt(candidate -> candidate.getRoom().getCapacity())
//                .thenComparing(candidate -> candidate.getRoom().getRoomId())
//        );
//        return ordered;
//    }
//}
