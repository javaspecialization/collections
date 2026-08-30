---
name: jira-review
description: Stage 4 of the Jira implementation pipeline. Given any Jira ticket key or URL (usage - /jira-review <KEY>), review the current branch's implementation against that Jira ticket - business-requirements coverage and technical quality - by delegating to the be-reviewer subagent, then surface docs/jira/<KEY>-review.md. Runnable standalone in a fresh session. Invoke explicitly by name.
---

# jira-review — Stage 4: review

Input: a Jira key or URL (`$ARGUMENTS`). Output: `docs/jira/<KEY>-review.md` + a one-line verdict.

Works standalone — it does not assume `/jira-spec` or `/jira-plan` ran in this session.

## Steps

1. **Delegate to the `be-reviewer` subagent** (Agent tool, `subagent_type: "be-reviewer"`).
   Pass in the message:
   - the Jira key or URL,
   - `docs/jira/<KEY>-spec.md` and `docs/jira/<KEY>-plan.md` **if they exist** (context only —
     the ticket is the yardstick),
   - the rubric: assess Block 1 (business-requirements coverage) and Block 2 (technical
     implementation), then write `docs/jira/<KEY>-review.md`. See `references/review-rubric.md`.

2. **Surface the result**: print the verdict
   (Approve / Approve-with-nits / Changes-requested), the must-fix list, and the path to
   `docs/jira/<KEY>-review.md`.

## Rules

- Review only. Neither this skill nor the subagent edits product code.
- If Jira is unreachable, the `be-reviewer` subagent (via the `jira` skill) will ask for the
  pasted ticket rather than guessing.
