# Delivery System LLD (Interview Refactored)

## Overall Feature Requirements
Design a Delivery Management and Payment System. The system needs to track multiple **Drivers** and their ongoing/completed **Deliveries** (Orders). Drivers are strictly paid on an hourly basis. You need to be able to assign deliveries, calculate total costs, settle partial payments up to specific time thresholds, and provide analytical data about peak driver concurrency.

---

## Phase-By-Phase Extension Guide

Because writing everything at once is risky in a 60-minute interview, this codebase is structured progressively:

### Phase 1: Core System & Cost Calculation (The Foundation)
**What we implemented:**
*   Basic entity definitions: `Driver` and `Delivery`.
*   The ability to onboard a `Driver` with an `hourlyRate`.
*   The ability to assign a `Delivery` to a specific driver with a given `startTime` and `endTime` (all tracked in seconds or milliseconds).
*   Logic to cleanly calculate the total base cost of any individual delivery using the formula: `duration * hourlyRate`.

### Phase 2: Partial Settlements & Payment Tracking
**What we extend:**
*   **The Interview Twist:** In the real world, companies run payment batches (e.g., every end of day) while some deliveries might span across midnight or haven't finished yet. How do you pay a driver for the work done *so far* without overpaying them later?
*   **Code Extension:** 
    *   Added a `settledUpToTime` tracker marker exactly inside the `Delivery` class. 
    *   Developed the `settlePayment(driverId, upToTime)` loop the Delivery System list. This successfully isolates the unsettled portion, calculates the payout, and pushes the `settledUpToTime` marker exactly to `upToTime`.

### Phase 3: Analytics (Maximum Concurrent Active Drivers)
**What we extend:**
*   **The Interview Twist:** The interviewer changes gears from purely OOP (Object Oriented Programming) logic to Algorithmic capability. They will ask: *"Can you find what the maximum number of distinct drivers active precisely at the same time were, over a 24-hour period?"*
*   **Code Extension:**
    *   Implemented a standard **Sweep Line Algorithm**. 
    *   Converted every relevant overlap into an `Event` object: When a delivery starts, we create a `+1` Event. When it ends, a `-1` Event. 
    *   By sorting events purely by time and keeping a running dictionary, we find the absolute peak concurrency limit in highly efficient $O(N \log N)$ time, instead of failing loops.

---

**Tip for your Interview:**
1. Tell the interviewer upfront: *"I will first build the core layout (Phase 1). After it succeeds, I will inject the settlement logic (Phase 2), and completely separate out the algorithms for concurrency (Phase 3)."* 
2. Proceed deliberately step-by-step just like these folders do.
