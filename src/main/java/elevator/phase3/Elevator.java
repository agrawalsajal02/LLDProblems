package elevator.phase3;

import java.util.HashSet;
import java.util.Set;

public final class Elevator {
    private final int elevatorId;
    private final int minFloor;
    private final int maxFloor;
    private final Set<Request> requests;
    private int currentFloor;
    private Direction direction;

    public Elevator(int elevatorId, int minFloor, int maxFloor) {
        this.elevatorId = elevatorId;
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
        this.currentFloor = minFloor;
        this.direction = Direction.IDLE;
        this.requests = new HashSet<>();
    }

    public boolean addRequest(Request request) {
        return isRequestAcceptable(request) && requests.add(request);
    }

    public void step() {
        if (requests.isEmpty()) {
            direction = Direction.IDLE;
            return;
        }

        if (direction == Direction.IDLE) {
            direction = chooseDirectionTowardNearestRequest();
        }

        if (shouldStopAtCurrentFloor()) {
            clearCurrentFloorStops();
            if (requests.isEmpty()) {
                direction = Direction.IDLE;
            }
            return;
        }

        if (!hasAnyRequestAhead(direction)) {
            direction = reverse(direction);
            return;
        }

        currentFloor = nextFloorInCurrentDirection();
    }

    public boolean hasRequestsAtOrBeyond(int floor, Direction requestedDirection) {
        for (Request request : requests) {
            if (requestedDirection == Direction.UP && request.getFloor() >= floor) {
                if (request.getType() == RequestType.PICKUP_UP || request.getType() == RequestType.DESTINATION) {
                    return true;
                }
            }
            if (requestedDirection == Direction.DOWN && request.getFloor() <= floor) {
                if (request.getType() == RequestType.PICKUP_DOWN || request.getType() == RequestType.DESTINATION) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getElevatorId() {
        return elevatorId;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    private boolean isRequestAcceptable(Request request) {
        return request.getFloor() >= minFloor
            && request.getFloor() <= maxFloor
            && request.getFloor() != currentFloor;
    }

    private Direction chooseDirectionTowardNearestRequest() {
        Request nearest = findNearestRequest();
        return nearest.getFloor() > currentFloor ? Direction.UP : Direction.DOWN;
    }

    private Request findNearestRequest() {
        Request nearest = null;
        int minDistance = Integer.MAX_VALUE;
        for (Request request : requests) {
            int distance = Math.abs(request.getFloor() - currentFloor);
            if (distance < minDistance
                || (distance == minDistance && (nearest == null || request.getFloor() < nearest.getFloor()))) {
                nearest = request;
                minDistance = distance;
            }
        }
        return nearest;
    }

    private boolean shouldStopAtCurrentFloor() {
        if (requests.contains(new Request(currentFloor, RequestType.DESTINATION))) {
            return true;
        }
        if (direction == Direction.UP) {
            return requests.contains(new Request(currentFloor, RequestType.PICKUP_UP));
        }
        if (direction == Direction.DOWN) {
            return requests.contains(new Request(currentFloor, RequestType.PICKUP_DOWN));
        }
        return false;
    }

    private void clearCurrentFloorStops() {
        requests.remove(new Request(currentFloor, RequestType.DESTINATION));
        if (direction == Direction.UP) {
            requests.remove(new Request(currentFloor, RequestType.PICKUP_UP));
        } else if (direction == Direction.DOWN) {
            requests.remove(new Request(currentFloor, RequestType.PICKUP_DOWN));
        }
    }

    private boolean hasAnyRequestAhead(Direction currentDirection) {
        for (Request request : requests) {
            if (currentDirection == Direction.UP && request.getFloor() > currentFloor) {
                return true;
            }
            if (currentDirection == Direction.DOWN && request.getFloor() < currentFloor) {
                return true;
            }
        }
        return false;
    }

    private Direction reverse(Direction currentDirection) {
        return currentDirection == Direction.UP ? Direction.DOWN : Direction.UP;
    }

    private int nextFloorInCurrentDirection() {
        if (direction == Direction.UP) {
            return currentFloor + 1;
        }
        return currentFloor - 1;
    }
}
