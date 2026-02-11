package elevator.extensibility;

import java.util.HashSet;
import java.util.Set;

import elevator.Direction;
import elevator.Request;
import elevator.RequestType;

public class ExtElevator {
    private int currentFloor;
    private Direction direction;
    private final Set<Request> requests;

    public ExtElevator() {
        this.currentFloor = 0;
        this.direction = Direction.IDLE;
        this.requests = new HashSet<>();
    }

    public boolean addRequest(int floor, RequestType type) {
        if (floor < 0 || floor > 9) {
            return false;
        }
        if (floor == currentFloor) {
            return true;
        }
        return requests.add(new Request(floor, type));
    }

    public boolean removeRequest(int floor, RequestType type) {
        return requests.remove(new Request(floor, type));
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }
}
