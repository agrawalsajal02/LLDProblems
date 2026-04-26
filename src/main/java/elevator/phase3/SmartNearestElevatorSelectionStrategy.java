package elevator.phase3;

import java.util.List;

public final class SmartNearestElevatorSelectionStrategy implements ElevatorSelectionStrategy {
    @Override
    public Elevator select(List<Elevator> elevators, Request request) {
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

    private Elevator findCommittedElevator(List<Elevator> elevators, Request request) {
        // Hall call se request ki travel direction nikaal lo.
        Direction requestDirection = request.getType() == RequestType.PICKUP_UP ? Direction.UP : Direction.DOWN;
        // Ab tak ki best committed elevator aur uski distance track karenge.
        Elevator nearest = null;
        int minDistance = Integer.MAX_VALUE;

        for (Elevator elevator : elevators) {
            // Sirf wahi elevators consider karo jo sach me request ki taraf move kar rahi hain.
            if (!isMovingTowardRequest(elevator, request.getFloor(), requestDirection)) {
                continue;
            }

            // Agar elevator waise bhi is floor tak ya uske aage tak nahi jayegi, to usse promise mat karo.
            if (!elevator.hasRequestsAtOrBeyond(request.getFloor(), requestDirection)) {
                continue;
            }

            // Current request tak elevator kitni door hai, wo nikaal lo.
            int distance = Math.abs(elevator.getCurrentFloor() - request.getFloor());
            // Jo committed elevator sabse paas ho, use best choice banao.
            if (distance < minDistance) {
                minDistance = distance;
                nearest = elevator;
            }
        }
        // Agar suitable committed elevator mili, to wahi return hogi, warna null.
        return nearest;
    }

    private boolean isMovingTowardRequest(Elevator elevator, int requestedFloor, Direction requestDirection) {
        // Elevator ki direction request ki direction se match nahi karti, to ye suitable nahi hai.
        if (elevator.getDirection() != requestDirection) {
            return false;
        }
        // UP request ke liye elevator requested floor ke neeche ya usi floor par honi chahiye.
        if (requestDirection == Direction.UP) {
            return elevator.getCurrentFloor() <= requestedFloor;
        }
        // DOWN request ke liye elevator requested floor ke upar ya usi floor par honi chahiye.
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
        Elevator nearest = elevators.get(0);
        int minDistance = Math.abs(nearest.getCurrentFloor() - requestedFloor);

        for (Elevator elevator : elevators) {
            int distance = Math.abs(elevator.getCurrentFloor() - requestedFloor);
            if (distance < minDistance) {
                minDistance = distance;
                nearest = elevator;
            }
        }
        return nearest;
    }
}
