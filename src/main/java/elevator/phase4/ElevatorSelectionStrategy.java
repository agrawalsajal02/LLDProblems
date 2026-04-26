package elevator.phase4;

import java.util.List;

public interface ElevatorSelectionStrategy {
    Elevator select(List<Elevator> elevators, Request request, Elevator expressElevator);
}
