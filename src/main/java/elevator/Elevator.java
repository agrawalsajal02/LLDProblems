package elevator;

import java.util.HashSet;
import java.util.Set;

public class Elevator {
    private int currentFloor;
    private Direction direction;
    private final Set<Request> requests;

    public Elevator() {
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

    public void step() {

        // Agar koi request hi nahi hai
        // → elevator IDLE ho jata hai aur return.
        if (requests.isEmpty()) {
            direction = Direction.IDLE;
            return;
        }

        // Agar elevator IDLE hai aur requests hain
        // → nearest request dhundo
        // → uski taraf direction set karo (UP ya DOWN).
        if (direction == Direction.IDLE) {
            Request nearest = null;
            int minDistance = Integer.MAX_VALUE;

            for (Request req : requests) {
                int distance = Math.abs(req.getFloor() - currentFloor);
                if (distance < minDistance ||
                        (distance == minDistance && (nearest == null || req.getFloor() < nearest.getFloor()))) {
                    minDistance = distance;
                    nearest = req;
                }
            }

            direction = (nearest.getFloor() > currentFloor) ? Direction.UP : Direction.DOWN;
        }

        // Agar current floor par stop karna hai
        // (pickup type match ya destination match)
        // → request remove karo
        // → agar ab koi request nahi बची → IDLE
        // → warna agar aage wali direction me koi request nahi → direction reverse
        // → return (is tick me move nahi karega).

        RequestType pickupType = (direction == Direction.UP) ? RequestType.PICKUP_UP : RequestType.PICKUP_DOWN;
        Request pickupRequest = new Request(currentFloor, pickupType);
        Request destinationRequest = new Request(currentFloor, RequestType.DESTINATION);

        if (requests.contains(pickupRequest) || requests.contains(destinationRequest)) {
            requests.remove(pickupRequest);
            requests.remove(destinationRequest);

            if (requests.isEmpty()) {
                direction = Direction.IDLE;
                return;
            }
            if (!hasRequestsAhead(direction)) {
                direction = (direction == Direction.UP) ? Direction.DOWN : Direction.UP;
            }
            return;
        }

        // Agar current direction me aage koi request nahi hai
        // → direction reverse karo aur return.
        if (!hasRequestsAhead(direction)) {
            direction = (direction == Direction.UP) ? Direction.DOWN : Direction.UP;
            return;
        }

        // Otherwise move one floor
        // → direction UP ho to floor++
        // → direction DOWN ho to floor--
        if (direction == Direction.UP) {
            currentFloor++;
        } else if (direction == Direction.DOWN) {
            currentFloor--;
        }
    }

    public boolean hasRequestsAhead(Direction dir) {
        for (Request request : requests) {
            if (dir == Direction.UP && request.getFloor() > currentFloor) {
                return true;
            }
            if (dir == Direction.DOWN && request.getFloor() < currentFloor) {
                return true;
            }
        }
        return false;
    }

    public boolean hasRequestsAtOrBeyond(int floor, Direction dir) {
        for (Request request : requests) {
            if (dir == Direction.UP && request.getFloor() >= floor) {
                if (request.getType() == RequestType.PICKUP_UP || request.getType() == RequestType.DESTINATION) {
                    return true;
                }
            }
            if (dir == Direction.DOWN && request.getFloor() <= floor) {
                if (request.getType() == RequestType.PICKUP_DOWN || request.getType() == RequestType.DESTINATION) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }
}
