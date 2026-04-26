# Meeting Room Scheduler Extensibility

## 1. Concurrency
Current version single-threaded soch ke banaya gaya hai.

If interviewer asks:
- add lock per room
- final booking recheck under write lock

Implemented here:
- `ThreadSafeRoom.java`
- `ThreadSafeFirstAvailableMeetingScheduler.java`
- `ThreadSafeMain.java`

## Thread-safe approach used
- each room has its own `ReentrantReadWriteLock`
- `isAvailable()` uses read lock
- `trySchedule()` uses write lock
- scheduler first discovers candidate rooms
- then tries booking one by one
- final success/failure is decided only inside room write lock

### Why this works
Naive flow:
- check room free
- then book

Problem:
- two threads same room ko simultaneously free dekh sakte hain

Safe flow:
- read phase only shortlist banata hai
- actual booking write lock ke andar hoti hai
- agar koi aur thread already book kar chuka ho, `trySchedule()` null return karega

Memory line:

`Read phase suggest karta hai, write lock decide karta hai`

## 2. Capacity support
This version says “allocate any room”.

If capacity requirement bhi add karna ho:
- add `requiredCapacity` in `MeetingRequest`
- add `capacity` in `Room`
- filter before availability check

## 3. Better room selection strategy
Can add:
- best fit capacity
- minimum spillage
- round robin
- least utilized

## 4. Audit logs
Per room booking history store kar sakte ho.

## 5. Recurring meetings
Current implementation one-time meetings ke liye hai.
Recurring support ke liye:
- recurrence rule
- occurrence expansion
- each occurrence availability check

## 6. Notifications
Abhi simple console notification hai.
Production mein:
- email
- push
- SMS
- async queue

## Easy memory line

`Base solve karo with TreeMap range query`

`Extend bolo with locks, capacity, better strategy, audit, recurrence`
