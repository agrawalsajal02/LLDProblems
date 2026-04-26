package companiesProblem.uber.lld.meetingroom.trainmanagementsystemheap;

import java.util.List;

public final class FirstAvailablePlatformStrategy implements PlatformAssignmentStrategy {
    @Override
    public Platform choose(List<Platform> availablePlatforms) {
        return availablePlatforms.isEmpty() ? null : availablePlatforms.get(0);
    }
}
