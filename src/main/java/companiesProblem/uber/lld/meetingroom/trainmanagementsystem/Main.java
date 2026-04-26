package companiesProblem.uber.lld.meetingroom.trainmanagementsystem;

import java.time.LocalDateTime;

public final class Main {
    public static void main(String[] args) {
        TrainPlatformManagementSystem system = new TrainPlatformManagementSystem();

        system.addPlatform(new Platform("P1"));
        system.addPlatform(new Platform("P2"));

        system.addTrain(new Train("T1"));
        system.addTrain(new Train("T2"));

        TrainAssignment assignment = system.assignTrainToPlatform(
            new AssignmentRequest(
                "T1",
                new TimeSlot(
                    LocalDateTime.of(2026, 4, 10, 10, 0),
                    LocalDateTime.of(2026, 4, 10, 10, 30)
                )
            )
        );

        System.out.println("Assigned: " + assignment);

        System.out.println(
            "Train at P1 at 10:10 = "
                + system.getTrainAtPlatformAtTime("P1", LocalDateTime.of(2026, 4, 10, 10, 10)).getTrainId()
        );

        System.out.println(
            "Platform for T1 at 10:10 = "
                + system.getPlatformForTrainAtTime("T1", LocalDateTime.of(2026, 4, 10, 10, 10)).getPlatformId()
        );
    }
}
