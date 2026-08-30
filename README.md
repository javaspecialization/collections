# collections

A from-scratch implementation exercise of a custom generic collection framework.
`com.example.List<E>` is the contract — its JavaDoc is the authoritative spec — and
`com.example.LinkedList<E>` is the implementation being filled in method by method, one
Jira ticket at a time.

## Build & test

Java 21, Gradle 9.3 via the wrapper. Use `./gradlew` (Git Bash) or `.\gradlew.bat` (PowerShell).

```bash
./gradlew build                                              # compile + test
./gradlew test                                               # all tests
./gradlew test --tests "com.example.LinkedListTest"          # one class
./gradlew test --tests "com.example.LinkedListTest.addRejectsNull"   # one method
```

Test report: `build/reports/tests/test/index.html`. There is no `run` task and no linter.

## Jira implementation pipeline

Ticket work runs through four stages in Claude Code. Each is invoked explicitly with a Jira
ticket key or URL — `<KEY>` below stands for whichever ticket you pass (e.g. `PROJ-42`).
Handoff documents land in `docs/jira/` (git-tracked).

```
1. /jira-spec    <KEY>   → fetch ticket, create branch <KEY>-*, write docs/jira/<KEY>-spec.md, you confirm
2. /jira-plan    <KEY>   → read the spec, write docs/jira/<KEY>-plan.md, you accept
3. /jira-develop <KEY>   → be-developer implements the plan + tests, runs ./gradlew test, stages changes (no commit)
4. /jira-review  <KEY>   → be-reviewer diffs the branch vs main against the ticket,
                           writes docs/jira/<KEY>-review.md (business coverage + technical quality)
```

- Stages 1–2 are interactive: they loop on your edits before writing the handoff file.
- Stage 3 stages changes but **never commits or pushes**.
- Stage 4 is read-only and can be run in a fresh session (it re-fetches the ticket).

### Jira access

The Atlassian MCP server is configured but authenticated per session. Run `/mcp` to
authenticate, or let the `jira` skill prompt you. If Jira is unreachable, paste the ticket
(key, summary, description, acceptance criteria) and the pipeline uses it verbatim.
See `.claude/skills/jira/references/mcp-setup.md`.

## Repo guidance

`CLAUDE.md` has the architecture notes and conventions (interface-as-contract, no `null`
elements, verbatim JavaDoc, JUnit 5 edge-case tests). The `.github/` folder keeps the original
GitHub Copilot assets; the `.claude/` folder is the Claude Code equivalent.
