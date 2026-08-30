# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## What this project is

A from-scratch implementation exercise of a custom generic collection framework. `com.example.List<E>` is the **contract** — its JavaDoc is the authoritative spec — and `com.example.LinkedList<E>` is the implementation being filled in method by method. Work is driven by Jira tickets (see `.github/`), one ticket per method or small group of methods.

## Build & test

Java 21, Gradle 9.3 via wrapper. On Windows use `.\gradlew.bat`; the examples below use `./gradlew`.

- Build + test: `./gradlew build`
- Run all tests: `./gradlew test`
- Single test class: `./gradlew test --tests "com.example.LinkedListTest"`
- Single test method: `./gradlew test --tests "com.example.LinkedListTest.addRejectsNull"`
- Clean: `./gradlew clean`
- Test report (HTML): `build/reports/tests/test/index.html`

There is no `application` plugin, so no `./gradlew run` task; `Main` is a placeholder. There is no linter/formatter configured — match surrounding style by hand.

## Architecture & conventions

- **`List.java` is the source of truth.** Method semantics — return values, thrown exceptions, null-handling, index-bound rules — are defined by its JavaDoc. Implement `LinkedList` to match that JavaDoc exactly; do not change the interface or its contract to make an implementation easier.
- **No `null` elements.** Every mutator rejects `null` with `NullPointerException` (via `Objects.requireNonNull`). Read/query methods (`contains`, `indexOf`) accept `null` args and simply return "not found". Do not add `null` element support anywhere the JavaDoc forbids it.
- **`LinkedList` internals:** singly linked, with `head`, `tail`, and an `int size` kept in sync by every mutator. Private helpers: `nodeAt(index)` (walk), `checkElementIndex` (`0 <= i < size`, for access) vs `checkPositionIndex` (`0 <= i <= size`, for insertion). Nodes are an immutable-value inner `static final class Node<E>` (`value` final, `next` mutable).
- **JavaDoc style:** the interface method's JavaDoc is duplicated verbatim onto the implementing method. Keep them identical when editing. Style is concise with `@param`/`@return`/`@throws`, matching `List.java`.
- **Tests:** JUnit 5 (`org.junit.jupiter`). One `@Test` per behavior group, descriptive names (`methodDoesXWhenY`), covering happy path plus edges: null args, empty list, index `-1` / `0` / `size` / `size+1`, first/middle/last positions. Add tests alongside each implemented method.

## Jira implementation pipeline

Ticket work runs through four explicit stages (`.claude/skills/` + `.claude/agents/`). Each takes
a Jira ticket key or URL; `<KEY>` below is a placeholder for whichever ticket is passed. Handoff
docs live in `docs/jira/` and are git-tracked so they show up in the PR.

| Stage | Invoke | Does | Handoff |
|---|---|---|---|
| 1. spec | `/jira-spec <KEY>` | Fetches the ticket, creates/checks out the `<KEY>-*` branch, drafts the spec, confirms with you (loops on edits) | `docs/jira/<KEY>-spec.md` |
| 2. plan | `/jira-plan <KEY>` | Reads the spec, drafts an implementation plan, waits for your acceptance | `docs/jira/<KEY>-plan.md` |
| 3. develop | `/jira-develop <KEY>` | Delegates to the `be-developer` subagent: implements the plan + JUnit 5 tests, runs `./gradlew test`, **stages** changes | _(none — staged code)_ |
| 4. review | `/jira-review <KEY>` | Delegates to the `be-reviewer` subagent: diffs branch vs `main` against the ticket, scores business coverage + technical quality | `docs/jira/<KEY>-review.md` |

- The shared **`jira`** skill fetches/parses a ticket (auth handoff + paste fallback); stages 1 and 4 use it.
- `be-developer` **never commits or pushes** — it stages only.
- `be-reviewer` is **read-only** on product code and is safe to run in a fresh session (it re-fetches the ticket and does not assume the spec/plan exist).
- The old Copilot assets under `.github/` (`agents/`, `prompts/`, `copilot-instructions.md`) are kept as-is for Copilot users; the `.claude/` pipeline is the Claude Code equivalent.

## Working style (from `.github/copilot-instructions.md` and `.github/agents/be-developer.agent.md`)

- Keep changes minimal and behavior-preserving unless the ticket explicitly asks for a behavior change. Do not touch unrelated files for cleanup/formatting.
- Preserve public API signatures and documented exception behavior.
- Branch naming: `<JIRA-KEY>-<short-kebab-summary>` (e.g. `PROJ-42-add-remove-method`).
- The `be-developer` Copilot agent workflow stages intended files but **never commits or pushes** — leave changes staged for review. Follow the same default here unless the user asks otherwise.
- Jira tickets: title prefix `JSL-`, with a clear description and specific, testable acceptance criteria referencing the relevant files/methods.

## Jira / MCP

Ticket-implementation flows expect Atlassian MCP auth. The server is configured for Claude Code but authenticated per session — run `/mcp` (or the `jira` skill will prompt). If Jira is unreachable, paste the ticket (key, summary, description, acceptance criteria) and the `jira` skill uses it verbatim rather than guessing. Setup: `.claude/skills/jira/references/mcp-setup.md` (Claude Code) and `.github/instructions/atlassian-mcp-setup.md` (background).