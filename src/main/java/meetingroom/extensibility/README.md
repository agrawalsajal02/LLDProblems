# Meeting Room Scheduler Extensibility

## 1. More room selection strategies
Currently:
- minimum spillage

Can add:
- best fit capacity
- round robin
- least utilized room
- building / floor preference

## 2. User availability
Current problem assumes attendees available hain.

Future:
- per-user calendar maintain karo
- meeting schedule tabhi allow karo jab:
- room available ho
- all users available hon

## 3. Recurring meetings
Current implementation one-time meetings ke liye hai.

Future:
- recurring rule object
- each occurrence validate karo
- partial success / all-or-nothing strategy decide karo

## 4. Equipment / room features
Room filters add kar sakte ho:
- projector
- whiteboard
- VC setup
- floor / building

## 5. Persistent storage
Abhi in-memory hai.

Production version:
- room bookings DB mein
- unique / overlap-safe booking transaction
- audit logs separate store mein

## 6. Audit cleanup scheduler
Current code `purgeAuditLogs(retention)` manually call karta hai.

Future:
- scheduled background cleanup job

## 7. Concurrency improvements
Current approach:
- per-room read/write lock
- final booking under write lock

Future:
- candidate retry budget
- optimistic retry metrics
- hot room mitigation

## 8. Multi-building scale
Room indexing add karo:
- capacity
- building
- floor
- feature tags

Then scheduler saare rooms scan nahi karega.

## Easy memory line

`Core solve karo: room, overlap, strategy, lock`

`Extension bolo: users, recurrence, features, DB, cleanup`
