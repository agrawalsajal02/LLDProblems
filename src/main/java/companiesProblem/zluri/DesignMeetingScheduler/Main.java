package companiesProblem.zluri.DesignMeetingScheduler;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public final class Main {
    public static void main(String[] args) {
        MeetingSchedulerService schedulerService = new MeetingSchedulerService(new ConsoleNotificationService());

        long firstStart = toEpochMillis(2026, 3, 27, 10, 0);
        long firstEnd = toEpochMillis(2026, 3, 27, 10, 30);
        long recurrenceEnd = toEpochMillis(2026, 3, 29, 10, 30);

        String scheduleId = schedulerService.createSchedule(
            new CreateScheduleRequest(
                firstStart,
                firstEnd,
                recurrenceEnd,
                "sajal",
                RecurrenceType.DAILY,
                Arrays.asList("alice", "bob")
            )
        );

        long day = toEpochMillis(2026, 3, 28, 0, 0);
        List<MeetingInstance> meetings = schedulerService.getMeetingsForDay("alice", day);
        System.out.println("Meetings for Alice on 2026-03-28: " + meetings.size());

        for (MeetingInstance meeting : meetings) {
            System.out.println(
                "Meeting " + meeting.getMeetingId() + " start=" + meeting.getStartTime() + " end=" + meeting.getEndTime()
            );
        }

        schedulerService.deleteSchedule(scheduleId);
        System.out.println("Meetings after delete: " + schedulerService.getMeetingsForDay("alice", day).size());
    }

    private static long toEpochMillis(int year, int month, int day, int hour, int minute) {
        return LocalDateTime.of(year, month, day, hour, minute).toInstant(ZoneOffset.UTC).toEpochMilli();
    }
}
