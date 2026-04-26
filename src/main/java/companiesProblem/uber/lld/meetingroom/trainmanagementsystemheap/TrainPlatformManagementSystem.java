package companiesProblem.uber.lld.meetingroom.trainmanagementsystemheap;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.UUID;

public final class TrainPlatformManagementSystem {
    private final Map<String, Train> trainsById;
    private final Map<String, Platform> platformsById;
    private final Map<String, List<TrainAssignment>> trainHistoryByTrainId;
    private final Map<String, List<TrainAssignment>> platformHistoryByPlatformId;
    private final PriorityQueue<TrainAssignment> activeAssignmentsByEndTime;
    private final Set<String> activeTrainIds;
    private final Set<String> occupiedPlatformIds;
    private final PlatformAssignmentStrategy assignmentStrategy;

    public TrainPlatformManagementSystem(PlatformAssignmentStrategy assignmentStrategy) {
        this.trainsById = new HashMap<>();
        this.platformsById = new HashMap<>();
        this.trainHistoryByTrainId = new HashMap<>();
        this.platformHistoryByPlatformId = new HashMap<>();
        this.activeAssignmentsByEndTime = new PriorityQueue<>(
            Comparator.comparing(assignment -> assignment.getTimeSlot().getEnd())
        );
        this.activeTrainIds = new HashSet<>();
        this.occupiedPlatformIds = new HashSet<>();
        this.assignmentStrategy = assignmentStrategy;
    }

    public void addTrain(Train train) {
        trainsById.put(train.getTrainId(), train);
        trainHistoryByTrainId.putIfAbsent(train.getTrainId(), new ArrayList<>());
    }

    public void addPlatform(Platform platform) {
        platformsById.put(platform.getPlatformId(), platform);
        platformHistoryByPlatformId.putIfAbsent(platform.getPlatformId(), new ArrayList<>());
    }

    public TrainAssignment assignTrain(AssignmentRequest request) {
        Train train = trainsById.get(request.getTrainId());
        if (train == null) {
            throw new NotFoundException("Train not found: " + request.getTrainId());
        }

        // Assumption: requests are coming sorted by start time.
        // Is assumption ki wajah se hum min-heap se sirf completed active assignments release kar sakte hain.
        releaseCompletedAssignments(request.getTimeSlot().getStart());

        if (activeTrainIds.contains(request.getTrainId())) {
            throw new AssignmentConflictException("Train is already active: " + request.getTrainId());
        }

        List<Platform> availablePlatforms = getCurrentlyAvailablePlatforms();
        Platform chosenPlatform = assignmentStrategy.choose(availablePlatforms);
        if (chosenPlatform == null) {
            throw new AssignmentConflictException("No platform available for " + request.getTimeSlot());
        }

        TrainAssignment assignment = new TrainAssignment(
            UUID.randomUUID().toString(),
            train,
            chosenPlatform,
            request.getTimeSlot()
        );

        activeAssignmentsByEndTime.offer(assignment);
        activeTrainIds.add(train.getTrainId());
        occupiedPlatformIds.add(chosenPlatform.getPlatformId());
        trainHistoryByTrainId.get(train.getTrainId()).add(assignment);
        platformHistoryByPlatformId.get(chosenPlatform.getPlatformId()).add(assignment);
        return assignment;
    }

    public Train getTrainAtPlatformAtTime(String platformId, LocalDateTime pointInTime) {
        List<TrainAssignment> history = platformHistoryByPlatformId.get(platformId);
        if (history == null) {
            throw new NotFoundException("Platform not found: " + platformId);
        }

        TrainAssignment assignment = findAssignmentAt(history, pointInTime);
        return assignment == null ? null : assignment.getTrain();
    }

    public Platform getPlatformForTrainAtTime(String trainId, LocalDateTime pointInTime) {
        List<TrainAssignment> history = trainHistoryByTrainId.get(trainId);
        if (history == null) {
            throw new NotFoundException("Train not found: " + trainId);
        }

        TrainAssignment assignment = findAssignmentAt(history, pointInTime);
        return assignment == null ? null : assignment.getPlatform();
    }

    private void releaseCompletedAssignments(LocalDateTime currentStartTime) {
        while (!activeAssignmentsByEndTime.isEmpty()) {
            TrainAssignment earliestEnding = activeAssignmentsByEndTime.peek();
            if (earliestEnding.getTimeSlot().getEnd().isAfter(currentStartTime)) {
                break;
            }

            activeAssignmentsByEndTime.poll();
            activeTrainIds.remove(earliestEnding.getTrain().getTrainId());
            occupiedPlatformIds.remove(earliestEnding.getPlatform().getPlatformId());
        }
    }

    private List<Platform> getCurrentlyAvailablePlatforms() {
        List<Platform> availablePlatforms = new ArrayList<>();
        for (Platform platform : platformsById.values()) {
            if (!occupiedPlatformIds.contains(platform.getPlatformId())) {
                availablePlatforms.add(platform);
            }
        }
        availablePlatforms.sort(Comparator.comparing(Platform::getPlatformId));
        return availablePlatforms;
    }

    private TrainAssignment findAssignmentAt(List<TrainAssignment> history, LocalDateTime pointInTime) {
        int low = 0;
        int high = history.size() - 1;
        TrainAssignment answer = null;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            TrainAssignment candidate = history.get(mid);
            if (candidate.getTimeSlot().getStart().isAfter(pointInTime)) {
                high = mid - 1;
            } else {
                answer = candidate;
                low = mid + 1;
            }
        }

        return answer != null && answer.getTimeSlot().contains(pointInTime) ? answer : null;
    }
}
