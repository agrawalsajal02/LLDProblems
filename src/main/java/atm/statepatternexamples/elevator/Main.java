package atm.statepatternexamples.elevator;

public final class Main {
    public static void main(String[] args) {
        Elevator elevator = new Elevator();
        elevator.requestFloor(3);
        elevator.step();
        elevator.step();
        elevator.step();
        elevator.step();
    }
}
