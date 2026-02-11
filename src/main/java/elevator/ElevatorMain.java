package elevator;

public final class ElevatorMain {
    public static void main(String[] args) {
        ElevatorController controller = new ElevatorController();

        controller.requestElevator(5, Direction.UP);
        controller.requestDestination(0, 8);
        controller.requestElevator(2, Direction.DOWN);

        for (int i = 0; i < 10; i++) {
            controller.step();
        }
    }
}
