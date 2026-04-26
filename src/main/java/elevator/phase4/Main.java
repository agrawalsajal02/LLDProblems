package elevator.phase4;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        ElevatorController controller = new ElevatorController(
            3,
            0,
            9,
            new ExpressAwareElevatorSelectionStrategy()
        );

        controller.requestElevator(5, RequestType.PICKUP_UP);
        controller.requestElevator(8, RequestType.PICKUP_DOWN);
        controller.requestDestination(0, 9);
        controller.cancelRequest(0, 9, RequestType.DESTINATION);

        for (int tick = 1; tick <= 10; tick++) {
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
                    + " express=" + elevator.isExpress()
            );
        }
    }
}
