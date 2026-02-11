package elevator.extensibility;

import java.util.HashSet;
import java.util.Set;

import elevator.Elevator;
import elevator.RequestType;

public class ExpressElevator extends Elevator {
    private final Set<Integer> expressFloors;

    public ExpressElevator(Set<Integer> expressFloors) {
        super();
        this.expressFloors = new HashSet<>(expressFloors);
    }

    @Override
    public boolean addRequest(int floor, RequestType type) {
        if (!expressFloors.contains(floor)) {
            return false;
        }
        return super.addRequest(floor, type);
    }
}
