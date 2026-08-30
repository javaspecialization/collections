---
name: jira-spec
description: Stage 1 of the Jira implementation pipeline. Given any Jira ticket key or URL (usage - /jira-spec <KEY>), fetch the ticket, create/checkout the feature branch, and write docs/jira/<KEY>-spec.md - a specification with functional requirements, explicit test requirements, and acceptance criteria - then confirm it with the user (looping on edits) before handing off to /jira-plan. Invoke explicitly by name.
---

# jira-spec — Stage 1: specification

Input: a Jira key or URL (`$ARGUMENTS`). Output: `docs/jira/<KEY>-spec.md`, confirmed by the user.

## Steps

1. **Feature branch.**
   - Run `git rev-parse --abbrev-ref HEAD`.
   - If already on `<KEY>-*`, stay.
   - Else if a local branch matching `<KEY>-*` exists, `git checkout` it.
   - Else `git checkout main && git pull --ff-only` (best effort) then
     `git checkout -b <KEY>-<short-kebab-summary>` where the summary comes from the ticket.
   - Never proceed on `main`.

2. **Fetch the ticket.** Use the `jira` skill with the key. You get key, summary, status,
   description, acceptance criteria, relevant comments.

3. **Draft the spec** at `docs/jira/<KEY>-spec.md` using `references/spec-template.md`. Fill every
   section:
   - **Business context** — what the ticket is for, in prose.
   - **Scope** — in scope / out of scope, as bullet lists.
   - **Functional requirements** — one entry per method or behavior. Cite the contract in
     `src/main/java/com/example/List.java` (its JavaDoc is authoritative): return value,
     exceptions, null-handling, index-bound rules.
   - **Test requirements** — enumerate concrete cases the tests must cover:
     null argument(s); empty list; index `-1`, `0`, `size`, `size + 1`; first / middle / last
     position; duplicate elements; and any behavior-specific edges from the ticket.
   - **Acceptance criteria** — restate the ticket's criteria verbatim, then add any derived
     criteria. Each must be specific and testable.

4. **Confirmation gate.**
   - Summarize the spec to the user in a few lines and ask: *"Is this spec correct?"*
   - If **no**: collect their edits, rewrite `docs/jira/<KEY>-spec.md`, summarize again, re-ask.
     Repeat until approved.
   - If **yes**: leave the file in place and tell the user to run `/jira-plan <KEY>`.

## Rules

- The ticket is the source of truth. If it is ambiguous, list the open questions in the spec
  under an **Open questions** heading and ask the user rather than guessing.
- Do not write code or tests in this stage — only the spec document.
