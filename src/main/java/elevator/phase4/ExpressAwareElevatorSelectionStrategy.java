package elevator.phase4;

import java.util.List;
import java.util.Set;

public final class ExpressAwareElevatorSelectionStrategy implements ElevatorSelectionStrategy {
    private static final Set<Integer> EXPRESS_FLOORS = Set.of(0, 5, 9);

    @Override
    public Elevator select(List<Elevator> elevators, Request request, Elevator expressElevator) {
        // Agar request express floor ki hai aur express elevator free hai, to usse prefer karo.
        if (isExpressFloor(request.getFloor()) && expressElevator != null && expressElevator.getDirection() == Direction.IDLE) {
            return expressElevator;
        }

        Elevator best = findCommittedElevator(elevators, request);
        if (best != null) {
            return best;
        }

        best = findNearestIdleElevator(elevators, request.getFloor());
        if (best != null) {
            return best;
        }

        return findNearestElevator(elevators, request.getFloor());
    }

    private boolean isExpressFloor(int floor) {
        return EXPRESS_FLOORS.contains(floor);
    }

    private Elevator findCommittedElevator(List<Elevator> elevators, Request request) {
        Direction requestDirection = request.getType() == RequestType.PICKUP_UP ? Direction.UP : Direction.DOWN;
        Elevator nearest = null;
        int minDistance = Integer.MAX_VALUE;

        for (Elevator elevator : elevators) {
            // Sirf wahi elevator lo jo request ki taraf move kar rahi hai.
            if (!isMovingTowardRequest(elevator, request.getFloor(), requestDirection)) {
                continue;
            }
            // Sirf wahi elevator lo jo waise bhi is floor tak ya aage tak jane wali hai.
            if (!elevator.hasRequestsAtOrBeyond(request.getFloor(), requestDirection)) {
                continue;
            }

            int distance = Math.abs(elevator.getCurrentFloor() - request.getFloor());
            if (distance < minDistance) {
                minDistance = distance;
                nearest = elevator;
            }
        }
        return nearest;
    }

    private boolean isMovingTowardRequest(Elevator elevator, int requestedFloor, Direction requestDirection) {
        if (elevator.getDirection() != requestDirection) {
            return false;
        }
        if (requestDirection == Direction.UP) {
            return elevator.getCurrentFloor() <= requestedFloor;
        }
        return elevator.getCurrentFloor() >= requestedFloor;
    }

    private Elevator findNearestIdleElevator(List<Elevator> elevators, int requestedFloor) {
        Elevator nearest = null;
        int minDistance = Integer.MAX_VALUE;

        for (Elevator elevator : elevators) {
            if (elevator.getDirection() != Direction.IDLE) {
                continue;
            }
            int distance = Math.abs(elevator.getCurrentFloor() - requestedFloor);
            if (distance < minDistance) {
                minDistance = distance;
                nearest = elevator;
            }
        }
        return nearest;
    }

    private Elevator findNearestElevator(List<Elevator> elevators, int requestedFloor) {
        Elevator nearest = null;
        int minDistance = Integer.MAX_VALUE;

        for (Elevator elevator : elevators) {
            int distance = Math.abs(elevator.getCurrentFloor() - requestedFloor);
            if (nearest == null || distance < minDistance) {
                minDistance = distance;
                nearest = elevator;
            }
        }
        return nearest;
    }
}
