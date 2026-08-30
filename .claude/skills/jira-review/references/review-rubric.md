# Review rubric — /jira-review

The review file `docs/jira/<KEY>-review.md` has exactly two assessment blocks plus a verdict.

## Verdict scale

- **Approve** — all acceptance criteria covered, no correctness issues, new code adequately tested.
- **Approve-with-nits** — criteria covered and correct; only minor clean-code / style points remain.
- **Changes-requested** — a missing/partial acceptance criterion, a correctness bug, or new code
  without tests.

## Block 1 — Business requirements coverage

Yardstick: the **Jira ticket** (description + acceptance criteria). The spec/plan are context only.

Produce a table:

| # | Acceptance criterion (from ticket) | Status | Evidence |
|---|---|---|---|
| 1 | <verbatim> | Covered / Partial / Missing | `LinkedList.java:57` |

Then note any behavior implemented **beyond** the ticket's scope.

## Block 2 — Technical implementation

- **Correctness** — logic vs the `com.example.List` JavaDoc contract; edge cases (null, index
  `-1`/`0`/`size`/`size+1`, empty, first/middle/last, duplicates); exception **types** match the
  contract; `size` / `head` / `tail` stay consistent.
- **Clean code** — descriptive names; small methods; no duplicated traversal (helpers
  `nodeAt` / `checkElementIndex` / `checkPositionIndex` reused); no dead code; formatting matches
  the surrounding file; implementation JavaDoc copied verbatim from the interface.
- **Test coverage** — every new/changed method has JUnit 5 tests; edge cases from the ticket/spec
  are asserted; run `./gradlew test` and record pass/fail; call out untested branches.

Tag each finding **[must-fix]** or **[nice-to-have]**.

## File shape

```
# <KEY> — Review

Verdict: <Approve | Approve-with-nits | Changes-requested>
Reviewed: <branch> vs main  ·  <N> files changed
Jira: <KEY> — <summary> (<status>)

## Block 1 — Business requirements coverage
<table + out-of-scope notes>

## Block 2 — Technical implementation
### Correctness
### Clean code
### Test coverage
`./gradlew test`: <PASS/FAIL — counts>

## Must-fix
- ...

## Nice-to-have
- ...
```
