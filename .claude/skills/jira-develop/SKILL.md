---
name: jira-develop
description: Stage 3 of the Jira implementation pipeline. Given any Jira ticket key (usage - /jira-develop <KEY>), implement the accepted docs/jira/<KEY>-plan.md by delegating to the be-developer subagent, then relay its summary. Invoke explicitly by name, after /jira-plan.
---

# jira-develop — Stage 3: implementation

Input: a Jira key (`$ARGUMENTS`). Output: staged code + test changes on the feature branch.
No handoff document.

## Steps

1. **Preconditions.**
   - `docs/jira/<KEY>-plan.md` exists — if not, stop and tell the user to run `/jira-plan <KEY>`.
   - Current branch is `<KEY>-*` — if not, stop and tell the user to run `/jira-spec <KEY>` first.

2. **Delegate to the `be-developer` subagent** (Agent tool, `subagent_type: "be-developer"`).
   Pass in the message:
   - the Jira key,
   - the path `docs/jira/<KEY>-plan.md` (source of truth),
   - the path `docs/jira/<KEY>-spec.md` (context),
   - the instruction: implement the plan, add/adjust JUnit 5 tests, run `./gradlew test`,
     stage the intended files, do **not** commit or push.

3. **Relay the subagent's report**: Jira key, branch, files changed, validations run
   (`./gradlew test` result), and `git status`. If the subagent flagged plan gaps or
   ambiguities, surface them to the user.

## Rules

- This skill does not edit code itself — it only orchestrates and reports.
- Do not commit or push on the user's behalf.
