---
name: jira
description: Fetch and parse any Jira ticket (given its key like PROJ-42 or its URL) via the Atlassian MCP server. Verifies/authenticates access, extracts summary, status, description, acceptance criteria, and relevant comments, and falls back to user-pasted text when MCP is unavailable. Use whenever a task needs the contents of a Jira ticket - notably at the start of /jira-spec and inside /jira-review.
---

# jira — read a Jira ticket

Shared capability. Given a Jira ticket key or a Jira issue URL, return a normalized ticket
block that other stages consume. Works for any project/ticket. Never invent ticket content.

## Steps

1. **Resolve the key.** Accept a bare key (`<PROJECT>-<NUMBER>`) or a URL such as
   `https://<site>.atlassian.net/browse/<PROJECT>-<NUMBER>`. Extract the bare key.

2. **Check Atlassian MCP access.** Attempt a read, in order:
   - `getAccessibleAtlassianResources` (or the equivalent `mcp__atlassian__*` tool) to confirm a site is reachable.
   - `getJiraIssue` / `mcp__atlassian__getJiraIssue` for the resolved key.

3. **If access is missing** (only `mcp__atlassian__authenticate` / `complete_authentication`
   are available, or calls return `Unauthorized` / `Forbidden`):
   - Tell the user Jira is not authenticated and run `mcp__atlassian__authenticate`.
   - Walk them through `mcp__atlassian__complete_authentication` with the value it returns.
   - Retry step 2 once.
   - See `references/mcp-setup.md` for the Claude Code specifics.

4. **If Jira is still unreachable**, ask the user to paste:
   - key, summary, status, full description, and the acceptance criteria (verbatim).
   Treat the pasted text as the source of truth. Do **not** guess missing fields — ask.

5. **Extract and normalize.** Produce this block for the caller:

   ```
   Key:                 <KEY>
   Summary:             <one line>
   Status:              <e.g. To Do / In Progress>
   Description:         <full text, trimmed>
   Acceptance criteria: <bullet list, verbatim where possible>
   Relevant comments:   <only comments that change scope or requirements; omit chatter>
   ```

## Notes

- Read-only. This skill never edits Jira. If the caller explicitly needs a Jira update
  (comment, transition), that is a separate, user-approved action.
- The project key is whatever the caller passes — do not assume a fixed project.
