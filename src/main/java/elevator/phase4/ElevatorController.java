package elevator.phase4;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public final class ElevatorController {
    private final int minFloor;
    private final int maxFloor;
    private final List<Elevator> elevators;
    private final ElevatorSelectionStrategy elevatorSelectionStrategy;
    private final Elevator expressElevator;

    public ElevatorController(int elevatorCount, int minFloor, int maxFloor, ElevatorSelectionStrategy elevatorSelectionStrategy) {
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
        this.elevatorSelectionStrategy = elevatorSelectionStrategy;
        this.elevators = new ArrayList<>();

        Elevator selectedExpressElevator = null;
        for (int index = 0; index < elevatorCount; index++) {
            boolean isExpress = index == 0;
            Elevator elevator = new Elevator(index, minFloor, maxFloor, isExpress, Set.of(0, 5, 9));
            elevators.add(elevator);
            if (isExpress) {
                selectedExpressElevator = elevator;
            }
        }
        this.expressElevator = selectedExpressElevator;
    }

    public synchronized boolean requestElevator(int floor, RequestType requestType) {
        if (!isValidHallCall(floor, requestType)) {
            return false;
        }
        Request request = new Request(floor, requestType);
        Elevator selectedElevator = elevatorSelectionStrategy.select(elevators, request, expressElevator);
        return selectedElevator != null && selectedElevator.addRequest(request);
    }

    public synchronized boolean requestDestination(int elevatorId, int destinationFloor) {
        if (destinationFloor < minFloor || destinationFloor > maxFloor) {
            return false;
        }
        Elevator elevator = findElevatorById(elevatorId);
        return elevator != null && elevator.addRequest(new Request(destinationFloor, RequestType.DESTINATION));
    }

    public synchronized boolean cancelRequest(int elevatorId, int floor, RequestType requestType) {
        Elevator elevator = findElevatorById(elevatorId);
        return elevator != null && elevator.removeRequest(new Request(floor, requestType));
    }

    public synchronized void step() {
        for (Elevator elevator : elevators) {
            elevator.step();
        }
    }

    public List<Elevator> getElevators() {
        return elevators;
    }

    public Elevator getExpressElevator() {
        return expressElevator;
    }

    private boolean isValidHallCall(int floor, RequestType requestType) {
        if (floor < minFloor || floor > maxFloor) {
            return false;
        }
        return requestType == RequestType.PICKUP_UP || requestType == RequestType.PICKUP_DOWN;
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
