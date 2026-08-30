# Atlassian MCP Setup (JetBrains)

Use this checklist to enable Jira access for Copilot Agent workflows in this repository.

## Why this is needed

`be-developer` is a local implementation agent. It can only execute tools declared in its front matter and does not include direct Jira tools. Jira data must be fetched by the caller/runtime through Atlassian MCP and then passed to the implementation agent.

## Current blocking signal

If Jira calls fail with `Unauthorized`, Atlassian MCP is reachable but not authenticated for your user/session.

## Required prerequisites

- You can sign in to your Jira site (for example `https://<your-site>.atlassian.net`).
- Your account has permission to read issues in the target project (for example `KAN`).
- Copilot Chat in JetBrains has MCP enabled.

## Configure Atlassian MCP in JetBrains

1. Open Settings/Preferences.
2. Navigate to Copilot MCP settings (`McpConfigurable`).
3. Add or enable the Atlassian MCP server.
4. Authenticate with Atlassian when prompted.
5. Approve access to the Jira site/workspace you need.
6. Save settings and restart the IDE if the MCP server does not appear immediately.

## Verify access

Run a Jira read test from chat before implementation:

- Check accessible resources.
- Fetch the target issue by key (for example `KAN-4`).

Expected behavior:

- Accessible resources returns your Jira cloud/site.
- Issue fetch returns summary/description/acceptance details.

If verification still fails:

- Re-authenticate Atlassian in MCP settings.
- Confirm the selected site is correct.
- Confirm project permissions for the ticket.
- Ensure corporate proxy/VPN policies are not blocking Atlassian endpoints.

## Recommended workflow in this repository

1. Use a top-level prompt/agent to fetch Jira via MCP.
2. Pass Jira key, summary, description, acceptance criteria, and relevant comments to `be-developer`.
3. Let `be-developer` create branch, implement, test, and stage changes (no commit).

## Claude Code

The steps above are for JetBrains + Copilot. In Claude Code the Atlassian MCP server is already
configured; it just needs a per-session sign-in.

1. Run `/mcp`, select `atlassian`, and authenticate (or call `mcp__atlassian__authenticate` and
   finish with `mcp__atlassian__complete_authentication`). Until then only the two auth tools are
   exposed — the Jira read tools appear after sign-in.
2. Verify by fetching any issue you can access.
3. The `jira` skill (`.claude/skills/jira/`) drives this automatically and falls back to a pasted
   ticket if sign-in is not possible. Claude Code specifics: `.claude/skills/jira/references/mcp-setup.md`.

