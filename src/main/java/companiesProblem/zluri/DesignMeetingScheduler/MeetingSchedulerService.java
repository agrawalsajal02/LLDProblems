package companiesProblem.zluri.DesignMeetingScheduler;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public final class MeetingSchedulerService {
    private final Map<String, Schedule> schedulesById;
    private final Map<String, List<MeetingInstance>> meetingsByScheduleId;
    private final Map<String, Map<Long, List<MeetingInstance>>> meetingsByUserAndDay;
    private final NotificationService notificationService;

    public MeetingSchedulerService(NotificationService notificationService) {
        this.schedulesById = new HashMap<>();
        this.meetingsByScheduleId = new HashMap<>();
        this.meetingsByUserAndDay = new HashMap<>();
        this.notificationService = notificationService;
    }

    public synchronized String createSchedule(CreateScheduleRequest request) {
        validateRequest(request);

        String scheduleId = UUID.randomUUID().toString();
        Schedule schedule = new Schedule(
            scheduleId,
            request.getFirstMeetingStartTime(),
            request.getFirstMeetingEndTime(),
            request.getRecurrenceEndTime(),
            request.getCreatedByUserId(),
            request.getRecurrenceType(),
            request.getParticipants()
        );

        List<MeetingInstance> meetingInstances = expandMeetings(schedule);
        validateNoConflicts(meetingInstances, null);

        schedulesById.put(scheduleId, schedule);
        meetingsByScheduleId.put(scheduleId, meetingInstances);
        indexMeetings(meetingInstances);

        // Hinglish: notification ko service ke peeche rakha hai, taaki later async queue se bhi bhej saken.
        notificationService.onScheduleCreated(scheduleId, request.getParticipants());
        return scheduleId;
    }

    public synchronized List<MeetingInstance> getMeetingsForDay(String userId, long dayEpochMillis) {
        long normalizedDay = normalizeDay(dayEpochMillis);
        Map<Long, List<MeetingInstance>> meetingsByDay = meetingsByUserAndDay.getOrDefault(userId, new HashMap<>());
        List<MeetingInstance> meetings = new ArrayList<>(meetingsByDay.getOrDefault(normalizedDay, new ArrayList<>()));
        meetings.removeIf(meeting -> meeting.getStatus() != ScheduleStatus.ACTIVE);
        meetings.sort(Comparator.comparingLong(MeetingInstance::getStartTime));
        return meetings;
    }

    public synchronized void deleteSchedule(String scheduleId) {
        Schedule schedule = schedulesById.get(scheduleId);
        if (schedule == null) {
            throw new ScheduleNotFoundException("Schedule not found: " + scheduleId);
        }

        schedule.markInactive();
        List<MeetingInstance> meetings = meetingsByScheduleId.getOrDefault(scheduleId, new ArrayList<>());
        for (MeetingInstance meeting : meetings) {
            meeting.markInactive();
        }
    }

    public synchronized String updateSchedule(String scheduleId, CreateScheduleRequest request) {
        Schedule existingSchedule = schedulesById.get(scheduleId);
        if (existingSchedule == null || existingSchedule.getStatus() != ScheduleStatus.ACTIVE) {
            throw new ScheduleNotFoundException("Active schedule not found: " + scheduleId);
        }

        validateRequest(request);

        Schedule newSchedulePreview = new Schedule(
            "PREVIEW",
            request.getFirstMeetingStartTime(),
            request.getFirstMeetingEndTime(),
            request.getRecurrenceEndTime(),
            request.getCreatedByUserId(),
            request.getRecurrenceType(),
            request.getParticipants()
        );

        List<MeetingInstance> previewMeetings = expandMeetings(newSchedulePreview);
        validateNoConflicts(previewMeetings, scheduleId);

        deleteSchedule(scheduleId);
        return createSchedule(request);
    }

    private void validateRequest(CreateScheduleRequest request) {
        if (request.getCreatedByUserId() == null || request.getCreatedByUserId().isEmpty()) {
            throw new InvalidScheduleException("Created user is required");
        }
        if (request.getParticipants() == null || request.getParticipants().isEmpty()) {
            throw new InvalidScheduleException("Participants are required");
        }
        if (request.getFirstMeetingStartTime() >= request.getFirstMeetingEndTime()) {
            throw new InvalidScheduleException("Meeting start time must be before end time");
        }
        if (request.getRecurrenceType() == null) {
            throw new InvalidScheduleException("Recurrence type is required");
        }
        if (request.getRecurrenceType() == RecurrenceType.ONCE) {
            if (request.getRecurrenceEndTime() != request.getFirstMeetingEndTime()) {
                throw new InvalidScheduleException("For once meetings, recurrenceEndTime should match firstMeetingEndTime");
            }
            return;
        }
        if (request.getRecurrenceEndTime() < request.getFirstMeetingEndTime()) {
            throw new InvalidScheduleException("Recurring schedule must have recurrenceEndTime after first meeting end time");
        }
    }

    private List<MeetingInstance> expandMeetings(Schedule schedule) {
        List<MeetingInstance> meetings = new ArrayList<>();
        long start = schedule.getFirstMeetingStartTime();
        long end = schedule.getFirstMeetingEndTime();
        long recurrenceEnd = schedule.getRecurrenceEndTime();
        long stepMillis = recurrenceStepMillis(schedule.getRecurrenceType());

        while (start <= recurrenceEnd) {
            long dayEpochMillis = normalizeDay(start);
            meetings.add(
                new MeetingInstance(
                    UUID.randomUUID().toString(),
                    schedule.getScheduleId(),
                    schedule.getCreatedByUserId(),
                    schedule.getParticipants(),
                    start,
                    end,
                    dayEpochMillis
                )
            );

            if (schedule.getRecurrenceType() == RecurrenceType.ONCE) {
                break;
            }

            start += stepMillis;
            end += stepMillis;
        }
        return meetings;
    }

    private void validateNoConflicts(List<MeetingInstance> newMeetings, String ignoredScheduleId) {
        for (MeetingInstance newMeeting : newMeetings) {
            for (String participant : newMeeting.getParticipants()) {
                Map<Long, List<MeetingInstance>> byDay = meetingsByUserAndDay.getOrDefault(participant, new HashMap<>());
                List<MeetingInstance> sameDayMeetings = byDay.getOrDefault(newMeeting.getDayEpochMillis(), new ArrayList<>());

                for (MeetingInstance existing : sameDayMeetings) {
                    if (existing.getStatus() != ScheduleStatus.ACTIVE) {
                        continue;
                    }
                    if (ignoredScheduleId != null && ignoredScheduleId.equals(existing.getScheduleId())) {
                        continue;
                    }
                    if (existing.overlaps(newMeeting.getStartTime(), newMeeting.getEndTime())) {
                        throw new ConflictException(
                            "Conflict for participant " + participant + " at " + newMeeting.getStartTime()
                        );
                    }
                }
            }
        }
    }

    private void indexMeetings(List<MeetingInstance> meetings) {
        for (MeetingInstance meeting : meetings) {
            Set<String> allUsers = new HashSet<>(meeting.getParticipants());
            allUsers.add(meeting.getCreatedByUserId());

            for (String userId : allUsers) {
                meetingsByUserAndDay
                    .computeIfAbsent(userId, ignored -> new HashMap<>())
                    .computeIfAbsent(meeting.getDayEpochMillis(), ignored -> new ArrayList<>())
                    .add(meeting);
            }
        }
    }

    private long recurrenceStepMillis(RecurrenceType recurrenceType) {
        if (recurrenceType == RecurrenceType.DAILY) {
            return 24L * 60 * 60 * 1000;
        }
        if (recurrenceType == RecurrenceType.WEEKLY) {
            return 7L * 24 * 60 * 60 * 1000;
        }
        return 0L;
    }

    private long normalizeDay(long epochMillis) {
        LocalDate day = Instant.ofEpochMilli(epochMillis).atZone(ZoneOffset.UTC).toLocalDate();
        return day.atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli();
    }
}
