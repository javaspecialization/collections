# <KEY> — Implementation Plan

> Source: docs/jira/<KEY>-spec.md. Accepted by the user. Input to /jira-develop.

## Goal

<one paragraph: what will exist when this is done>

## Files to change

- `src/main/java/com/example/LinkedList.java` — <what>
- `src/main/java/com/example/List.java` — <only if the contract itself changes>
- `src/test/java/com/example/LinkedListTest.java` — <new tests>

## Approach

### <method signature>
- Algorithm: <steps>
- Reuse: `nodeAt(...)` / `checkElementIndex(...)` / `checkPositionIndex(...)` / `Node` — <how>
- Bookkeeping: `size`, `head`, `tail` — <what changes>
- Exceptions: <type + when>
- JavaDoc: copy the interface method's JavaDoc verbatim onto the implementation.

### <next method>
- ...

## Edge-case handling

| Case | Handled by |
|---|---|
| null argument | `Objects.requireNonNull(...)` |
| index `-1` / `size + 1` | `checkPositionIndex` / `checkElementIndex` |
| empty list | <...> |
| first / middle / last | <...> |
| duplicates | <...> |

## Test plan (`LinkedListTest`, JUnit 5)

- `<methodName>DoesXWhenY()` — <asserts>
- `<methodName>RejectsNull()` — <asserts>
- `<methodName>ThrowsWhenIndexOutOfBounds()` — <asserts>

## Risks / tricky parts

- <...>

## Out of scope

- <...>
