# SRP Followed

## Design
- `Book` owns book-related logic.
- `BookPrinter` owns output behavior.

## Why this is better
- Domain logic and presentation change independently.
- Tests stay focused.

## Memory hook
- "Book for book logic, printer for printing."
