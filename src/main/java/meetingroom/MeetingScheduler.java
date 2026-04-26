package meetingroom;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class MeetingScheduler {
    private final List<Room> rooms;
    private final RoomSelectionStrategy roomSelectionStrategy;
    private final AuditLogger auditLogger;
    private final NotificationService notificationService;

    public MeetingScheduler(
        List<Room> rooms,
        RoomSelectionStrategy roomSelectionStrategy,
        AuditLogger auditLogger,
        NotificationService notificationService
    ) {
        this.rooms = new ArrayList<>(rooms);
        this.roomSelectionStrategy = roomSelectionStrategy;
        this.auditLogger = auditLogger;
        this.notificationService = notificationService;
    }

    public List<AvailableRoomView> findAvailableRooms(MeetingRequest request) {
        List<RoomCandidate> candidates = findCandidates(request);
        List<RoomCandidate> orderedCandidates = roomSelectionStrategy.orderCandidates(candidates);

        List<AvailableRoomView> result = new ArrayList<>();
        for (RoomCandidate candidate : orderedCandidates) {
            result.add(
                new AvailableRoomView(
                    candidate.getRoom().getRoomId(),
                    candidate.getRoom().getCapacity(),
                    candidate.getSpillageMinutes()
                )
            );
        }
        return result;
    }

    public Optional<Meeting> scheduleMeeting(MeetingRequest request) {
        List<RoomCandidate> candidates = findCandidates(request);
        if (candidates.isEmpty()) {
            return Optional.empty();
        }

        List<RoomCandidate> orderedCandidates = roomSelectionStrategy.orderCandidates(candidates);
        for (RoomCandidate candidate : orderedCandidates) {
            Meeting booked = candidate.getRoom().trySchedule(request);
            if (booked != null) {
                auditLogger.log(
                    booked.getRoomId(),
                    "Meeting " + booked.getMeetingId() + " scheduled for interval " + booked.getInterval()
                );
                notificationService.notifyMeetingScheduled(booked);
                return Optional.of(booked);
            }
        }

        return Optional.empty();
    }

    public void purgeAuditLogs(Duration retention) {
        auditLogger.purgeOlderThan(retention);
    }

    private List<RoomCandidate> findCandidates(MeetingRequest request) {
        List<RoomCandidate> candidates = new ArrayList<>();
        for (Room room : rooms) {
            RoomCandidate candidate = room.inspectCandidate(request);
            if (candidate != null) {
                candidates.add(candidate);
            }
        }
        return candidates;
    }
}
