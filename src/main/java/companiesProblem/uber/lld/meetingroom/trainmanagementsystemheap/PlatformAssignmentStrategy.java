package companiesProblem.uber.lld.meetingroom.trainmanagementsystemheap;

import java.util.List;

public interface PlatformAssignmentStrategy {
    Platform choose(List<Platform> availablePlatforms);
}
