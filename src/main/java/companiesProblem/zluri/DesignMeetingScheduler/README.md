# Design Meeting Scheduler

## Problem
Design a system similar to Google Calendar that supports:
- one-time meetings
- recurring meetings
- daily recurrence
- weekly recurrence

Required APIs:
- `createSchedule(...)`
- `getMeetingsForDay(userId, day)`
- `deleteSchedule(scheduleId)`
- `updateSchedule(scheduleId, request)`

## How to answer this in a 1-hour LLD interview

### What to finish in the interview
- Clarify recurrence scope: `ONCE`, `DAILY`, `WEEKLY`
- Model 2 core entities:
- `Schedule`
- `MeetingInstance`
- Implement these 4 APIs:
- `createSchedule`
- `getMeetingsForDay`
- `deleteSchedule`
- `updateSchedule`
- Explain conflict detection
- Explain why recurring schedules expand into meeting instances

### What not to overbuild
- time-zone engine
- reminders
- attendee RSVP workflow
- distributed locking
- persistence sharding
- Google Calendar-level sharing permissions

### Best interview pitch
- "I will store the recurring rule in `Schedule`, but precompute actual occurrences in `MeetingInstance` so that day-view and conflict-checking stay simple."

## What was weak in the draft
- Recurring schedules need one clear recurrence boundary. Without that, daily/weekly meetings can continue forever.
- Conflict validation cannot be done only at the top schedule row. It must be checked against actual meeting instances.
- `getMeetingToday()` becomes much easier if we materialize meeting instances and index by `userId + day`.
- `updateSchedule()` is tricky if done partially. For interview scope, a safe design is: validate new version first, then deactivate old schedule and create a replacement.

## Core design choice
We store two concepts:

### 1. `Schedule`
- Parent object
- Captures recurrence rules and ownership
- One row per logical schedule

### 2. `MeetingInstance`
- Child object
- One actual meeting occurrence
- Used for:
- day view
- conflict checking
- deletion
- notifications

This matches your original `ScheduleTable` + `MeetingTable` idea, but makes the responsibilities cleaner.

## Final requirements
- Support one-time meetings
- Support recurring meetings: daily and weekly
- Create schedule
- Get all meetings for a user on a given day
- Delete schedule
- Update schedule
- Prevent overlapping meetings for participants

## Out of scope
- Meeting room booking
- Reminders / email delivery guarantees
- Complex recurrence rules like "every 2nd Tuesday"
- Cross-timezone recurring rules
- External calendar sync

## In-memory data model

### `Schedule`
- `scheduleId`
- `createdByUserId`
- `participants`
- `recurrenceType` -> `ONCE`, `DAILY`, `WEEKLY`
- `firstMeetingStartTime`
- `firstMeetingEndTime`
- `recurrenceEndTime`
- `status`

### `MeetingInstance`
- `meetingId`
- `scheduleId`
- `createdByUserId`
- `participants`
- `startTime`
- `endTime`
- `dayEpochMillis`
- `status`

## API behavior

### `createSchedule`
- validate request shape
- expand recurring rule into meeting instances
- validate conflicts for every participant against every generated meeting
- if all valid:
- save schedule
- save meeting instances
- update `userId + day` index
- trigger async notification

### `getMeetingsForDay(userId, day)`
- normalize input day to start of day
- fetch indexed meetings
- filter only `ACTIVE`
- sort by start time

### `deleteSchedule(scheduleId)`
- mark schedule inactive
- mark all child meetings inactive

### `updateSchedule(scheduleId, request)`
- validate new request first
- ignore old schedule's own meetings during conflict detection
- if valid:
- mark old schedule inactive
- create new schedule

For interview scope, update returns a new schedule id. In production, this could be a versioned update inside a transaction.

## Main classes in code
- `CreateScheduleRequest`
- `Schedule`
- `MeetingInstance`
- `MeetingSchedulerService`
- `NotificationService`

## Key methods to focus on during interview coding
- `createSchedule`
- `expandMeetings`
- `validateNoConflicts`
- `getMeetingsForDay`

If time is short, these are the methods worth coding first.

## Why precompute meeting instances?
- Fast day lookup
- Easy overlap validation
- Easy delete
- Easy per-user notifications

## Trade-off
- More storage for recurring schedules
- But much simpler reads and simpler conflict logic

For LLD interviews, this is a very reasonable trade.

## Conflict rule
Two meetings overlap if:

```text
newStart < existingEnd AND existingStart < newEnd
```

If even one participant has an overlapping active meeting, schedule creation fails.

## Simplifications
- Time zone handling is kept simple
- We use epoch millis and normalize day using UTC
- Notifications are mocked behind an interface
- No persistence or distributed locks

## Mental model
- `Schedule` = recurring rule
- `MeetingInstance` = actual occurrence
- conflict check happens on instances
- day view also runs on instances

## What to say if interviewer asks "why not generate occurrences on the fly?"
- You can, but:
- conflict detection becomes harder
- day view becomes slower
- delete/update need recurrence expansion anyway
- precomputing keeps this design easy to reason about in interview scope

## What to say if interviewer asks "what are the trade-offs?"
- Precompute approach:
- simpler reads
- simpler validation
- more storage
- On-demand expansion:
- lower storage
- more complex reads and overlap checks

## Extensibility
- See `extensibility/README.md` for what to say if interviewer asks follow-up questions after the base design is done.
