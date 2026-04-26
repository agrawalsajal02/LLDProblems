package elevator.phase1;

import java.util.HashSet;
import java.util.Set;

public final class Elevator {
    private final int minFloor;
    private final int maxFloor;
    //elevator ke andar saari pending requests store karta hai.
    private final Set<Request> requests;
    private int currentFloor;
    private Direction direction;

    public Elevator(int minFloor, int maxFloor) {
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
        this.currentFloor = minFloor;
        this.direction = Direction.IDLE;
        this.requests = new HashSet<>();
    }

    public boolean addRequest(Request request) {
        // Agar request building ke valid floor range ke bahar hai, to usse reject karo.
        if (!isValidFloor(request.getFloor())) {
            return false;
        }
        // Agar elevator already isi floor par hai, to request ko already served maan lo.
        if (request.getFloor() == currentFloor) {
            return true;
        }
        // Request ko tabhi add karo jab wahi request pehle se pending na ho.
        return requests.add(request);
    }

    public void step() {
//        Idle -> Decide -> Stop -> Reverse -> Move
//        I D S R M
//        Pronounce kar lo:
//“Id-surm”

        // Agar koi pending request hi nahi hai, to elevator is tick me idle rahegi.
        if (requests.isEmpty()) {
            direction = Direction.IDLE;
            return;
        }

        // Agar elevator idle hai, to pehle nearest request ki taraf direction choose karo.
        if (direction == Direction.IDLE) {
            direction = chooseInitialDirection();
        }

        // Agar current floor par valid stop hai, to move karne se pehle isi floor ko serve karo.
        if (shouldStopAtCurrentFloor()) {
            removeServedRequestsAtCurrentFloor();
            // Agar is stop ke baad koi request nahi bachi, to elevator idle ho jaye.
            if (requests.isEmpty()) {
                direction = Direction.IDLE;
                return;
            }
            // Agar current direction me ab aage koi request nahi hai, to direction reverse karo.
            if (!hasAnyRequestAhead(direction)) {
                direction = reverse(direction);
            }
            return;
        }

        // Agar current direction me aage koi request nahi hai, to bina move kiye reverse karo.
        if (!hasAnyRequestAhead(direction)) {
            direction = reverse(direction);
            return;
        }

        // Normal case me elevator chosen direction me ek floor move karegi.
        moveOneFloor();
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    private boolean isValidFloor(int floor) {
        // Floor tabhi valid hai jab wo building ke configured min aur max ke beech ho.
        return floor >= minFloor && floor <= maxFloor;
    }

    private Direction chooseInitialDirection() {
        // Idle state me elevator nearest pending request ki taraf chalna start karti hai.
        // elevator is already idle , so there is no preference of directions
        Request nearest = findNearestRequest();
        return nearest.getFloor() > currentFloor ? Direction.UP : Direction.DOWN;
    }

    private Request findNearestRequest() {
        // Sabse nearest request nikaalo taaki starting direction deterministic rahe.
        Request nearest = null;
        int minDistance = Integer.MAX_VALUE;
        for (Request request : requests) {
            int distance = Math.abs(request.getFloor() - currentFloor);
            // Jo request zyada paas ho usse choose karo, aur tie ho to lower floor ko prefer karo.
            if (distance < minDistance
                || (distance == minDistance && (nearest == null || request.getFloor() < nearest.getFloor()))) {
                nearest = request;
                minDistance = distance;
            }
        }
        return nearest;
    }

    private boolean shouldStopAtCurrentFloor() {
        // Agar kisi passenger ne elevator ke andar se ye floor select kiya hai, to yahan rukna hi hai.
        if (requests.contains(new Request(currentFloor, RequestType.DESTINATION))) {
            return true;
        }
        // UP jaate waqt sirf un logon ko uthao jo isi floor se UP jana chahte hain.
        if (direction == Direction.UP) {
            return requests.contains(new Request(currentFloor, RequestType.PICKUP_UP));
        }
        // DOWN jaate waqt sirf un logon ko uthao jo isi floor se DOWN jana chahte hain.
        if (direction == Direction.DOWN) {
            return requests.contains(new Request(currentFloor, RequestType.PICKUP_DOWN));
        }
        // In sab ke alawa current tick me is floor par rukne ki zarurat nahi hai.
        return false;
    }

    private void removeServedRequestsAtCurrentFloor() {
        // Is floor par rukne ke baad yahan ka destination request hata do.
        requests.remove(new Request(currentFloor, RequestType.DESTINATION));
        // UP jaate waqt sirf pickup-up clear karo, pickup-down return trip ke liye pending rahega.
        if (direction == Direction.UP) {
            requests.remove(new Request(currentFloor, RequestType.PICKUP_UP));
        // DOWN jaate waqt sirf pickup-down clear karo, pickup-up baad me serve hoga.
        } else if (direction == Direction.DOWN) {
            requests.remove(new Request(currentFloor, RequestType.PICKUP_DOWN));
        }
    }

    private boolean hasAnyRequestAhead(Direction currentDirection) {
        // Check karo ki current direction me aage koi pending request hai ya nahi.
        for (Request request : requests) {
            // UP jaate waqt current floor se upar wali koi bhi request aage ka kaam hai.
            if (currentDirection == Direction.UP && request.getFloor() > currentFloor) {
                return true;
            }
            // DOWN jaate waqt current floor se neeche wali koi bhi request aage ka kaam hai.
            if (currentDirection == Direction.DOWN && request.getFloor() < currentFloor) {
                return true;
            }
        }
        // Agar aage koi request nahi mili, to current direction ka sweep khatam ho gaya.
        return false;
    }

    private Direction reverse(Direction currentDirection) {
        // Reverse ka matlab bas direction ko ulta kar do taaki doosri side ki requests serve ho saken.
        return currentDirection == Direction.UP ? Direction.DOWN : Direction.UP;
    }

    private void moveOneFloor() {
        // UP ka matlab current floor ko ek se badha do.
        if (direction == Direction.UP) {
            currentFloor++;
        // DOWN ka matlab current floor ko ek se ghata do.
        } else if (direction == Direction.DOWN) {
            currentFloor--;
        }
    }
}
