package elevator.phase3;

import java.util.ArrayList;
import java.util.List;

public final class ElevatorController {
    private final int minFloor;
    private final int maxFloor;
    private final List<Elevator> elevators;
    private final ElevatorSelectionStrategy elevatorSelectionStrategy;

    public ElevatorController(int elevatorCount, int minFloor, int maxFloor, ElevatorSelectionStrategy elevatorSelectionStrategy) {
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
        this.elevatorSelectionStrategy = elevatorSelectionStrategy;
        this.elevators = new ArrayList<>();
        for (int index = 0; index < elevatorCount; index++) {
            elevators.add(new Elevator(index, minFloor, maxFloor));
        }
    }

    public boolean requestElevator(int floor, RequestType requestType) {
        if (!isValidHallCall(floor, requestType)) {
            return false;
        }
        Request request = new Request(floor, requestType);
        Elevator selectedElevator = elevatorSelectionStrategy.select(elevators, request);
        return selectedElevator != null && selectedElevator.addRequest(request);
    }

    public boolean requestDestination(int elevatorId, int destinationFloor) {
        if (!isValidDestination(destinationFloor)) {
            return false;
        }
        Elevator elevator = findElevatorById(elevatorId);
        return elevator != null && elevator.addRequest(new Request(destinationFloor, RequestType.DESTINATION));
    }

    public void step() {
        for (Elevator elevator : elevators) {
            elevator.step();
        }
    }

    public List<Elevator> getElevators() {
        return elevators;
    }

    private boolean isValidHallCall(int floor, RequestType requestType) {
        if (floor < minFloor || floor > maxFloor) {
            return false;
        }
        return requestType == RequestType.PICKUP_UP || requestType == RequestType.PICKUP_DOWN;
    }

    private boolean isValidDestination(int floor) {
        return floor >= minFloor && floor <= maxFloor;
    }

    private Elevator findElevatorById(int elevatorId) {
        for (Elevator elevator : elevators) {
            if (elevator.getElevatorId() == elevatorId) {
                return elevator;
            }
        }
        return null;
    }
}
