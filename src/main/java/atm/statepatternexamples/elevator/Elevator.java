package atm.statepatternexamples.elevator;

public final class Elevator {
    private ElevatorState currentState;
    private int currentFloor;
    private int targetFloor;

    public Elevator() {
        this.currentState = new IdleState();
        this.currentFloor = 0;
        this.targetFloor = 0;
    }

    public void requestFloor(int floor) {
        currentState.requestFloor(this, floor);
    }

    public void step() {
        currentState.step(this);
    }

    public ElevatorState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(ElevatorState currentState) {
        this.currentState = currentState;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public int getTargetFloor() {
        return targetFloor;
    }

    public void setTargetFloor(int targetFloor) {
        this.targetFloor = targetFloor;
    }
}
