# Builder

## Use when
- Object has many optional fields.
- Constructor would become messy or full of `null`.

## Do not use when
- Object has only 2-3 required fields.
- A normal constructor is already readable.

## Memory hook
- "Builder constructs step by step, then `build()` gives the final object."

## Common shape to remember
- Outer class has private constructor taking builder.
- Builder has mandatory fields in its own constructor.
- Optional fields are set through chained setters.
- `build()` returns the final object.

## Interview triggers
- Pizza / food customization
- Query/config object
- Complex filter criteria
