package elevator.phase2;

import java.util.ArrayList;
import java.util.List;

public final class ElevatorController {
    private final int minFloor;
    private final int maxFloor;
    private final List<Elevator> elevators;

    public ElevatorController(int elevatorCount, int minFloor, int maxFloor) {
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
        this.elevators = new ArrayList<>();
        for (int index = 0; index < elevatorCount; index++) {
            elevators.add(new Elevator(index, minFloor, maxFloor));
        }
    }

    public boolean requestElevator(int floor, RequestType requestType) {
        if (!isValidHallRequest(floor, requestType)) {
            return false;
        }

        Elevator selectedElevator = selectNearestElevator(floor);
        return selectedElevator.addRequest(new Request(floor, requestType));
    }

    public boolean requestDestination(int elevatorId, int destinationFloor) {
        if (destinationFloor < minFloor || destinationFloor > maxFloor) {
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

    private boolean isValidHallRequest(int floor, RequestType requestType) {
        if (floor < minFloor || floor > maxFloor) {
            return false;
        }
        return requestType == RequestType.PICKUP_UP || requestType == RequestType.PICKUP_DOWN;
    }

    private Elevator selectNearestElevator(int requestedFloor) {
        Elevator nearest = elevators.get(0);
        int minDistance = Math.abs(nearest.getCurrentFloor() - requestedFloor);

        //“Har candidate ka score nikalo, aur agar score better ho toh score aur candidate dono replace karo.”
        for (Elevator elevator : elevators) {
            int distance = Math.abs(elevator.getCurrentFloor() - requestedFloor);
            if (distance < minDistance) {
                minDistance = distance;
                nearest = elevator;
            }
        }
        // score nikala he to find the candidate
        return nearest;
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
