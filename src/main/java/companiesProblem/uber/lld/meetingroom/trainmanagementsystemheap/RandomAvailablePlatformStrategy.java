package companiesProblem.uber.lld.meetingroom.trainmanagementsystemheap;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public final class RandomAvailablePlatformStrategy implements PlatformAssignmentStrategy {
    @Override
    public Platform choose(List<Platform> availablePlatforms) {
        if (availablePlatforms.isEmpty()) {
            return null;
        }
        int index = ThreadLocalRandom.current().nextInt(availablePlatforms.size());
        return availablePlatforms.get(index);
    }
}
