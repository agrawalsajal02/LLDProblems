package atm.statepatternexamples.elevator;

public abstract class ElevatorState {
    public void requestFloor(Elevator elevator, int floor) {
        throw new IllegalStateException("Request not allowed in " + getClass().getSimpleName());
    }

    public void step(Elevator elevator) {
        throw new IllegalStateException("Step not allowed in " + getClass().getSimpleName());
    }
}
