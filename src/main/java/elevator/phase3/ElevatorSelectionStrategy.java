package elevator.phase3;

import java.util.List;

public interface ElevatorSelectionStrategy {
    Elevator select(List<Elevator> elevators, Request request);
}
