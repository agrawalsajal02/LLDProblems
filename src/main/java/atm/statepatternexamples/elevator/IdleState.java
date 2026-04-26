package atm.statepatternexamples.elevator;

public final class IdleState extends ElevatorState {
    @Override
    public void requestFloor(Elevator elevator, int floor) {
        if (floor == elevator.getCurrentFloor()) {
            System.out.println("Elevator already at floor " + floor);
            return;
        }
        elevator.setTargetFloor(floor);
        // Idle state se request milte hi elevator decide karti hai ki upar jana hai ya neeche.
        elevator.setCurrentState(floor > elevator.getCurrentFloor() ? new MovingUpState() : new MovingDownState());
        System.out.println("Request accepted for floor " + floor);
    }

    @Override
    public void step(Elevator elevator) {
        System.out.println("Elevator is idle at floor " + elevator.getCurrentFloor());
    }
}
