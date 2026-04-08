---
name: verify-jira-access
description: Verify Atlassian MCP can access Jira before implementation.
---

/verify-jira-access

**Purpose:** Validate that Jira is accessible through Atlassian MCP before delegating implementation to `be-developer`.

**Input:**
- Jira key (example: `KAN-4`) or a Jira URL.

**Instructions:**
1. Use Atlassian MCP to list accessible resources.
2. Fetch the requested Jira issue.
3. Return:
   - Jira key,
   - summary,
   - issue status,
   - whether description and acceptance criteria are present.
4. If any Jira call fails with authentication or authorization errors, stop and report the exact missing prerequisite.

