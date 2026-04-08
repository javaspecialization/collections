---
name: implement-jira-ticket
description: Fetch a Jira ticket, then implement it locally through the be-developer agent.
---

/implement-jira-ticket

**Purpose:** Implement a Jira ticket in this repository with Jira as the source of truth.

**Input:**
- A Jira key such as `JSL-123`, or
- A Jira issue URL.

**Instructions:**
1. Use Atlassian MCP to fetch the Jira ticket details first.
2. Extract the Jira key, summary, description, acceptance criteria, and any implementation-relevant comments.
3. Delegate the implementation work to the `be-developer` agent.
4. Pass the fetched Jira details into the delegated task so the subagent does not need direct Jira tool access.
5. Require the subagent to:
   - create and checkout a branch based on the Jira key,
   - implement the change locally,
   - run validation and relevant tests,
   - stage the intended files,
   - avoid creating a commit.
6. Return a concise final summary with:
   - Jira key,
   - branch name,
   - files changed,
   - validations performed,
   - final git status.

