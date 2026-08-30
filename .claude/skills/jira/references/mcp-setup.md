# Atlassian MCP in Claude Code

Background and JetBrains/Copilot steps live in
`.github/instructions/atlassian-mcp-setup.md`. This file covers only what differs for
Claude Code.

## State in this repo

The Atlassian MCP server is already **configured** for Claude Code. Until you authenticate,
only `mcp__atlassian__authenticate` and `mcp__atlassian__complete_authentication` are
exposed; the Jira read tools (`getJiraIssue`, `searchJiraIssuesUsingJql`,
`getAccessibleAtlassianResources`, ...) appear only after a successful auth.

## Authenticate

1. Run `/mcp` and pick the `atlassian` server, or call `mcp__atlassian__authenticate`.
2. Open the URL it returns, approve access to the Jira site holding your tickets.
3. Complete with `mcp__atlassian__complete_authentication` using the returned code/value.
4. Re-run the Jira read to confirm (fetch any issue you can access).

## If it still fails

- Re-authenticate; confirm the selected site is correct.
- Confirm your account can read issues in the target project.
- Check corporate proxy/VPN is not blocking `*.atlassian.net` / `api.atlassian.com`.

## Fallback

If you cannot authenticate right now, paste the ticket (key, summary, status, description,
acceptance criteria) into the chat. The `jira` skill will use that verbatim as the source
of truth rather than guessing.
