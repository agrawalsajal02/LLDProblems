package companiesProblem.uber.lld.meetingroom.extensibility;

import companiesProblem.uber.lld.meetingroom.ConsoleNotificationService;
import companiesProblem.uber.lld.meetingroom.MeetingRequest;
import companiesProblem.uber.lld.meetingroom.TimeSlot;
import java.time.LocalDateTime;
import java.util.Arrays;

public final class ThreadSafeMain {
    public static void main(String[] args) {
        ThreadSafeRoom roomA = new ThreadSafeRoom("A");
        ThreadSafeRoom roomB = new ThreadSafeRoom("B");

        ThreadSafeFirstAvailableMeetingScheduler scheduler = new ThreadSafeFirstAvailableMeetingScheduler(
            Arrays.asList(roomA, roomB),
            new ConsoleNotificationService()
        );

        MeetingRequest request = new MeetingRequest(
            new TimeSlot(
                LocalDateTime.of(2026, 4, 10, 10, 0),
                LocalDateTime.of(2026, 4, 10, 11, 0)
            )
        );

        System.out.println("Available rooms before booking: " + scheduler.findAvailableRooms(request.getTimeSlot()).size());
        System.out.println("Booked? " + scheduler.scheduleMeeting(request).isPresent());
        System.out.println("Available rooms after booking: " + scheduler.findAvailableRooms(request.getTimeSlot()).size());
    }
}
