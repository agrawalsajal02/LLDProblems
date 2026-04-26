package companiesProblem.uber.lld.meetingscheuler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class MeetingScheduler {
    private final List<MeetingRoom> rooms;
    private final NotificationService notificationService;
    private final ScheduleRepository scheduleRepository;
    private final MeetingRepository meetingRepository;

    public MeetingScheduler(List<MeetingRoom> rooms, NotificationService notificationService) {
        this(rooms, notificationService, new ScheduleRepository(), new MeetingRepository());
    }

    public MeetingScheduler(
        List<MeetingRoom> rooms,
        NotificationService notificationService,
        ScheduleRepository scheduleRepository,
        MeetingRepository meetingRepository
    ) {
        this.rooms = new ArrayList<>(rooms);
        this.notificationService = notificationService;
        this.scheduleRepository = scheduleRepository;
        this.meetingRepository = meetingRepository;
    }

    public Meeting schedule(MeetingRequest request) {
        return createSchedule(request).get(0);
    }

    public List<Meeting> createSchedule(MeetingRequest request) {
        Schedule schedule = new Schedule(
            request.getTitle(),
            request.getOrganizer(),
            request.getParticipants(),
            request.getRequiredCapacity(),
            request.getTimeSlot(),
            request.getRecurrenceType(),
            request.getRecurrenceEndTime()
        );
        List<TimeSlot> occurrences = expandOccurrences(schedule);

        MeetingRoom selectedRoom = findAvailableRoom(request, occurrences);
        if (selectedRoom == null) {
            throw new IllegalStateException("No room available for the requested schedule.");
        }

        List<Meeting> meetings = new ArrayList<>();
        for (TimeSlot occurrence : occurrences) {
            Meeting meeting = new Meeting(
                schedule.getScheduleId(),
                schedule.getTitle(),
                schedule.getOrganizer(),
                schedule.getParticipants(),
                occurrence,
                selectedRoom
            );
            selectedRoom.addMeeting(meeting);
            meetings.add(meeting);
            notifyParticipants(meeting);
        }

        scheduleRepository.save(schedule);
        meetingRepository.saveAll(schedule.getScheduleId(), meetings);
        notificationService.notifyScheduleCreated(schedule, meetings);
        return meetings;
    }

    public MeetingRoom findAvailableRoom(MeetingRequest request) {
        return findAvailableRoom(request, List.of(request.getTimeSlot()));
    }

    private MeetingRoom findAvailableRoom(MeetingRequest request, List<TimeSlot> occurrences) {
        for (MeetingRoom room : rooms) {
            if (room.canHost(request.getRequiredCapacity()) && room.isAvailableForAll(occurrences)) {
                return room;
            }
        }
        return null;
    }

    public List<MeetingRoom> getRooms() {
        return Collections.unmodifiableList(rooms);
    }

    private void notifyParticipants(Meeting meeting) {
        String message = "Meeting '" + meeting.getTitle() + "' booked in room " + meeting.getRoom().getRoomId();
        notificationService.notifyUser(meeting.getOrganizer(), message);
        for (User participant : meeting.getParticipants()) {
            notificationService.notifyUser(participant, message);
        }
    }

    private List<TimeSlot> expandOccurrences(Schedule schedule) {
        List<TimeSlot> occurrences = new ArrayList<>();
        TimeSlot first = schedule.getFirstTimeSlot();
        occurrences.add(first);

        if (schedule.getRecurrenceType() == RecurrenceType.ONCE) {
            return occurrences;
        }

        LocalDateTime start = first.getStart();
        LocalDateTime end = first.getEnd();
        while (true) {
            start = nextStart(start, schedule.getRecurrenceType());
            end = nextStart(end, schedule.getRecurrenceType());
            if (end.isAfter(schedule.getRecurrenceEndTime())) {
                break;
            }
            occurrences.add(new TimeSlot(start, end));
        }
        return occurrences;
    }

    private LocalDateTime nextStart(LocalDateTime value, RecurrenceType recurrenceType) {
        switch (recurrenceType) {
            case DAILY:
                return value.plusDays(1);
            case WEEKLY:
                return value.plusWeeks(1);
            case ONCE:
            default:
                return value;
        }
    }
}
