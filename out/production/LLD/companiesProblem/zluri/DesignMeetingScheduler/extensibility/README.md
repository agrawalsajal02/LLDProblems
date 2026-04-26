# Meeting Scheduler Extensibility

Use this only after you finish the base interview solution. Do not start with this.

## 1. Time zones
- Store organizer timezone or schedule timezone
- Recurrence must be generated in that timezone
- DST makes recurrence tricky

## 2. Monthly recurrence and richer rules
- Add a `RecurrenceRule` object instead of plain enum
- Example fields:
- frequency
- interval
- byDay
- byMonthDay
- until

## 3. Meeting updates without full recreate
- Current design marks old schedule inactive and creates a new one
- Production systems may:
- version schedules
- update only future occurrences
- preserve past meeting history

## 4. Partial delete
- Delete only one occurrence from a recurring schedule
- Need an exception list:
- `skippedOccurrences`
- `overriddenOccurrences`

## 5. RSVP / attendee status
- Add per-user invitation status:
- `PENDING`
- `ACCEPTED`
- `DECLINED`
- `MAYBE`

## 6. Reminder / notification retries
- Current design uses a simple notification interface
- Production version should publish async events:
- schedule created
- schedule updated
- schedule deleted

## 7. Room/resource booking
- Treat room like another participant
- Conflict validation naturally extends to resources

## 8. Concurrency control
- In real systems, two users may create overlapping meetings at the same time
- Need:
- DB transaction
- row-level lock
- optimistic version check

## 9. Storage design if data becomes large
- `Schedule` table remains parent
- `MeetingInstance` table can be indexed by:
- `participantId + day`
- `scheduleId`
- `createdByUserId`

## 10. Interview-friendly line
- "For the base solution I would precompute occurrences. If the interviewer wants scale or richer recurrence, I would introduce recurrence rules, partial overrides, and a stronger persistence/indexing strategy."
