package elevator.phase1;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        Elevator elevator = new Elevator(0, 9);
        elevator.addRequest(new Request(3, RequestType.PICKUP_UP));
        elevator.addRequest(new Request(7, RequestType.DESTINATION));

        for (int tick = 1; tick <= 10; tick++) {
            elevator.step();
            System.out.println(
                "Tick=" + tick + ", floor=" + elevator.getCurrentFloor() + ", direction=" + elevator.getDirection()
            );
        }
    }
}
