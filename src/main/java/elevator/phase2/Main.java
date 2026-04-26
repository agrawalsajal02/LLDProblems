package elevator.phase2;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        ElevatorController controller = new ElevatorController(3, 0, 9);

        controller.requestElevator(3, RequestType.PICKUP_UP);
        controller.requestElevator(7, RequestType.PICKUP_DOWN);

        for (int tick = 1; tick <= 12; tick++) {
            if (tick == 3) {
                controller.requestDestination(0, 8);
            }
            controller.step();
            printState(controller, tick);
        }
    }

    private static void printState(ElevatorController controller, int tick) {
        System.out.println("Tick=" + tick);
        for (Elevator elevator : controller.getElevators()) {
            System.out.println(
                "Elevator " + elevator.getElevatorId()
                    + " floor=" + elevator.getCurrentFloor()
                    + " direction=" + elevator.getDirection()
            );
        }
    }
}
