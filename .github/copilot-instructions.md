# Copilot Instructions

This repository is a Java/Gradle project.

## General expectations
- Keep changes minimal and behavior-preserving unless a behavior change is explicitly requested.
- Prefer clear, concise code and documentation.
- Do not introduce `null` support where API/docs explicitly disallow `null`.
- Preserve public API contracts and exceptions.

## JavaDoc generation
- Use concise JavaDoc style.
- Include `@param`, `@return`, and `@throws` where relevant.
- Mention null-handling and index bounds when applicable.
- Match wording style used in `src/main/java/com/example/List.java`.

## Testing expectations
- Prefer JUnit 5.
- Cover happy path plus boundary/edge cases.
- Include null and index-bound checks when relevant.
- Use descriptive test names.

## Output preferences
- When asked for focused updates, return only changed code blocks.
- Keep explanations short and actionable.

