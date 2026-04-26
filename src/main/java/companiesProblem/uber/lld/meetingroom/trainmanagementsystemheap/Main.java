package companiesProblem.uber.lld.meetingroom.trainmanagementsystemheap;

import java.time.LocalDateTime;

public final class Main {
    public static void main(String[] args) {
        TrainPlatformManagementSystem system = new TrainPlatformManagementSystem(
            new FirstAvailablePlatformStrategy()
        );

        system.addPlatform(new Platform("P1"));
        system.addPlatform(new Platform("P2"));
        system.addTrain(new Train("T1"));
        system.addTrain(new Train("T2"));
        system.addTrain(new Train("T3"));

        TrainAssignment first = system.assignTrain(
            new AssignmentRequest(
                "T1",
                new TimeSlot(
                    LocalDateTime.of(2026, 4, 10, 10, 0),
                    LocalDateTime.of(2026, 4, 10, 10, 30)
                )
            )
        );

        TrainAssignment second = system.assignTrain(
            new AssignmentRequest(
                "T2",
                new TimeSlot(
                    LocalDateTime.of(2026, 4, 10, 10, 5),
                    LocalDateTime.of(2026, 4, 10, 10, 40)
                )
            )
        );

        TrainAssignment third = system.assignTrain(
            new AssignmentRequest(
                "T3",
                new TimeSlot(
                    LocalDateTime.of(2026, 4, 10, 10, 35),
                    LocalDateTime.of(2026, 4, 10, 11, 0)
                )
            )
        );

        System.out.println(first);
        System.out.println(second);
        System.out.println(third);

        System.out.println(
            "Train at P1 at 10:10 = "
                + system.getTrainAtPlatformAtTime("P1", LocalDateTime.of(2026, 4, 10, 10, 10)).getTrainId()
        );

        System.out.println(
            "Platform for T3 at 10:50 = "
                + system.getPlatformForTrainAtTime("T3", LocalDateTime.of(2026, 4, 10, 10, 50)).getPlatformId()
        );
    }
}
