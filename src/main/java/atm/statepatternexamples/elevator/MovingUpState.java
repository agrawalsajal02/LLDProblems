package atm.statepatternexamples.elevator;

public final class MovingUpState extends ElevatorState {
    @Override
    public void step(Elevator elevator) {
        elevator.setCurrentFloor(elevator.getCurrentFloor() + 1);
        System.out.println("Elevator moved up to floor " + elevator.getCurrentFloor());
        if (elevator.getCurrentFloor() == elevator.getTargetFloor()) {
            elevator.setCurrentState(new IdleState());
            System.out.println("Elevator reached target floor");
        }
    }
}
