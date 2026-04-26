# ISP Followed

## Design
- Interfaces are split by role: cleaning, feeding, petting.
- Implementers choose only the contracts they actually support.

## Why this is better
- No fake implementations.
- Role boundaries stay clear.

## Memory hook
- "Split by role, not by giant capability list."
