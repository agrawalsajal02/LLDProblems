package companiesProblem.uber.lld.meetingroom.trainmanagementsystem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public final class TrainPlatformManagementSystem {
    private final Map<String, PlatformCalendar> platformCalendarsById;
    private final Map<String, TrainCalendar> trainCalendarsById;

    public TrainPlatformManagementSystem() {
        this.platformCalendarsById = new HashMap<>();
        this.trainCalendarsById = new HashMap<>();
    }

    public void addPlatform(Platform platform) {
        platformCalendarsById.put(platform.getPlatformId(), new PlatformCalendar(platform));
    }

    public void addTrain(Train train) {
        trainCalendarsById.put(train.getTrainId(), new TrainCalendar(train));
    }

    public TrainAssignment assignTrainToPlatform(AssignmentRequest request) {
        TrainCalendar trainCalendar = trainCalendarsById.get(request.getTrainId());
        if (trainCalendar == null) {
            throw new NotFoundException("Train not found: " + request.getTrainId());
        }

        if (!trainCalendar.isAvailable(request.getTimeSlot())) {
            throw new AssignmentConflictException("Train is already assigned during " + request.getTimeSlot());
        }

        PlatformCalendar platformCalendar = findAvailablePlatform(request.getTimeSlot());
        if (platformCalendar == null) {
            throw new AssignmentConflictException("No platform available during " + request.getTimeSlot());
        }

        TrainAssignment assignment = new TrainAssignment(
            UUID.randomUUID().toString(),
            trainCalendar.getTrain(),
            platformCalendar.getPlatform(),
            request.getTimeSlot()
        );

        platformCalendar.addAssignment(assignment);
        trainCalendar.addAssignment(assignment);
        return assignment;
    }

    private PlatformCalendar findAvailablePlatform(TimeSlot requestedSlot) {
        List<PlatformCalendar> candidates = new ArrayList<>(platformCalendarsById.values());
        candidates.sort(Comparator.comparing(calendar -> calendar.getPlatform().getPlatformId()));

        for (PlatformCalendar platformCalendar : candidates) {
            if (platformCalendar.isAvailable(requestedSlot)) {
                return platformCalendar;
            }
        }
        return null;
    }

    public Train getTrainAtPlatformAtTime(String platformId, LocalDateTime pointInTime) {
        PlatformCalendar platformCalendar = platformCalendarsById.get(platformId);
        if (platformCalendar == null) {
            throw new NotFoundException("Platform not found: " + platformId);
        }

        TrainAssignment assignment = platformCalendar.findAssignmentAt(pointInTime);
        return assignment == null ? null : assignment.getTrain();
    }

    public Platform getPlatformForTrainAtTime(String trainId, LocalDateTime pointInTime) {
        TrainCalendar trainCalendar = trainCalendarsById.get(trainId);
        if (trainCalendar == null) {
            throw new NotFoundException("Train not found: " + trainId);
        }

        TrainAssignment assignment = trainCalendar.findAssignmentAt(pointInTime);
        return assignment == null ? null : assignment.getPlatform();
    }
}
