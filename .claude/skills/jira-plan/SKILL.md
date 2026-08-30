---
name: jira-plan
description: Stage 2 of the Jira implementation pipeline. Given any Jira ticket key (usage - /jira-plan <KEY>), read docs/jira/<KEY>-spec.md, inspect the codebase, and draft an implementation plan; on user acceptance write docs/jira/<KEY>-plan.md and hand off to /jira-develop. Invoke explicitly by name, after /jira-spec.
---

# jira-plan — Stage 2: implementation plan

Input: a Jira key (`$ARGUMENTS`). Output: `docs/jira/<KEY>-plan.md`, accepted by the user.

## Steps

1. **Read the spec** at `docs/jira/<KEY>-spec.md`. If it is missing, stop and tell the user to
   run `/jira-spec <KEY>` first. Confirm you are on the `<KEY>-*` branch.

2. **Inspect the codebase** before planning:
   - `src/main/java/com/example/List.java` (the contract),
   - `src/main/java/com/example/LinkedList.java` (current implementation + private helpers
     `nodeAt`, `checkElementIndex`, `checkPositionIndex`, inner `Node`),
   - `src/test/java/com/example/LinkedListTest.java` (test style and existing coverage).

3. **Draft the plan** at `docs/jira/<KEY>-plan.md` using `references/plan-template.md`:
   - Files to change.
   - Method-by-method approach — for each: signature, algorithm, which existing helpers to
     **reuse** (do not add a second list-traversal path if `nodeAt` already does it), size/`head`/
     `tail` bookkeeping, and the exact exceptions/messages.
   - Edge-case handling mapped to the spec's **Test requirements**.
   - Test plan — new `@Test` method names in `LinkedListTest`, one behavior group each.
   - Risks / tricky parts.
   - Explicitly out of scope.

4. **Acceptance gate.**
   - Present the plan summary and ask the user to accept it.
   - On requested changes: revise the file and re-ask.
   - On acceptance: leave `docs/jira/<KEY>-plan.md` in place and tell the user to run
     `/jira-develop <KEY>`.

## Rules

- Plan only what the spec supports. If the spec has **Open questions** still unresolved, resolve
  them with the user before writing the plan.
- No code changes in this stage.
