package elevator.phase3;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        ElevatorController controller = new ElevatorController(
            3,
            0,
            9,
            new SmartNearestElevatorSelectionStrategy()
        );

        controller.requestElevator(5, RequestType.PICKUP_UP);
        controller.requestElevator(8, RequestType.PICKUP_DOWN);

        for (int tick = 1; tick <= 15; tick++) {
            if (tick == 4) {
                controller.requestDestination(0, 9);
            }
            if (tick == 7) {
                controller.requestElevator(2, RequestType.PICKUP_UP);
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
