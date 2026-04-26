# LLM Router Using State Pattern

## Problem idea
- Router has 3 effective modes:
- `ClaudeHealthy`: send 100% traffic to Claude
- `ClaudeDegraded`: Claude failing, so send 95% OpenAI and 5% Claude
- `BothDegraded`: both failing, so send 5% OpenAI, 5% Claude, and drop the rest

## Why state pattern fits
- Routing logic changes based on current health mode.
- Transitions happen after request outcomes are known.
- Without state pattern, this becomes one large `if/else` block with fragile flags.

## Mental model
- Client asks router: "where should I send this request?"
- Router delegates decision to current state.
- After the request finishes, client reports result back.
- Current state decides whether to stay or transition.

## State transitions
- `ClaudeHealthy`
- Claude success -> stay in `ClaudeHealthy`
- Claude failure -> move to `ClaudeDegraded`

- `ClaudeDegraded`
- OpenAI success -> stay in `ClaudeDegraded`
- OpenAI failure -> move to `BothDegraded`
- Claude success -> move to `ClaudeHealthy`

- `BothDegraded`
- OpenAI success -> move to `ClaudeDegraded`
- Claude success -> move to `ClaudeHealthy`
- failures -> stay in `BothDegraded`

## Mapping to your requirements
- Claude healthy -> 100% Claude
- Claude failing -> 95% OpenAI, 5% Claude
- OpenAI also failing -> 5% OpenAI, 5% Claude, 90% ignored
- OpenAI recovered from degraded mode -> back to 95% OpenAI, 5% Claude
- Claude recovered from both degraded -> back to 100% Claude
