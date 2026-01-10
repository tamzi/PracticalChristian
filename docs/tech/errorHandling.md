# Error Handling

## Purpose

Prevent leakage of sensitive system information through error messages while preserving enough
internal detail for debugging.

## Scope

Applies to UI, presentation, domain, data, networking, sync, and storage layers across the app.

## Goals

- Avoid exposing internal system details, stack traces, or identifiers to users.
- Provide clear, actionable user-facing guidance.
- Preserve diagnostic context in logs and telemetry with redaction.
- Keep error UX consistent across features.

## Non-Goals

- Replacing crash reporting pipelines.
- Defining backend error payload schemas.

## Core Principles

- Separate user-facing copy from internal diagnostics.
- Default to generic, safe messages when the error is unknown.
- Normalize errors into stable categories before they reach UI.
- Redact sensitive data in all logs and analytics.
- Never render raw exception or backend messages in the UI.

## User-Facing Messaging

- Use short, plain-language copy without technical details.
- Provide a single next-step action when possible (retry, check connection, sign in again).
- Avoid exposing stack traces, IDs, endpoints, or database details.
- Prefer design system patterns for error and empty states. See:
  - `sacrament/src/main/java/com/sacrament/ui/patterns/SacramentErrorState.kt`
  - `sacrament/src/main/java/com/sacrament/ui/patterns/SacramentEmptyState.kt`
- For list state flows, ensure the UI state already holds sanitized copy. See:
  - `core/ui/src/main/java/com/practicalchristian/app/core/ui/helpers/ListState.kt`

## Internal Diagnostics

- Log detailed diagnostics only to internal logging/telemetry sinks.
- Include correlation IDs and error categories for traceability.
- Remove or mask secrets (tokens, auth headers, passwords), PII, and user content.
- Avoid storing raw backend payloads in UI state or persisted UI caches.

## Error Mapping Strategy

- Normalize errors at the data/network boundary into domain-level categories.
- Map domain categories to user-facing copy in presentation.
- Maintain a default mapping for unknown errors that is always safe.

## Error Categories and Copy Catalog (Draft)

Provide a consistent, safe message for each category. Keep strings short and non-technical.

- Network unavailable: "You're offline. Check your connection and try again."
- Timeout: "That took too long. Please try again."
- Unauthorized: "Please sign in again to continue."
- Forbidden: "You don't have access to this."
- Not found: "We couldn't find that. Try again."
- Conflict: "That change couldn't be saved. Try again."
- Validation: "Please check your input and try again."
- Rate limited: "Too many requests. Try again in a moment."
- Server error: "Something went wrong on our side. Try again."
- Unknown: "Something went wrong. Please try again."

## Error Code Taxonomy (Draft)

- Define stable categories at the domain boundary (network, auth, validation, conflict, not found).
- Keep internal codes separate from user-facing copy.
- Own mappings per layer:
  - Data/network layer: map raw errors to domain categories.
  - Domain layer: preserve category and add context tags.
  - Presentation layer: map category to user-facing copy.

## Robust Error Handling and Edge Case Management

Ensure error handling covers platform, lifecycle, and data edge cases with graceful degradation.

- Gate notification permission requests to Android 13+ only, and treat earlier SDKs as already granted.
- For permissions, handle deny/deny-and-don't-ask states with safe copy and a single action.
- When data is partial or empty, show safe empty states instead of errors.
- For offline-first flows, cache last known good data and show stale badges or timestamps.
- For long-running work, surface retry and cancellation paths.
- Ensure navigation side effects are idempotent to avoid repeat actions on recomposition.
- Avoid crashing on null/empty fields; show safe defaults in UI.

## Security and Privacy Considerations

- Treat stack traces, file paths, SQL, and internal IDs as sensitive.
- Avoid surfacing backend hostnames or endpoints.
- Ensure analytics and crash reports are scrubbed for PII and secrets.

## Verification Checklist

- UI does not render raw exception or backend messages.
- Error states show safe copy and a single action.
- Logs include correlation IDs and redaction.
- Sensitive fields are removed from telemetry payloads.

## Related References

- `core/ui/src/main/java/com/practicalchristian/app/core/ui/helpers/ListState.kt`
- `sacrament/src/main/java/com/sacrament/ui/patterns/SacramentErrorState.kt`
- `sacrament/src/main/java/com/sacrament/ui/patterns/SacramentEmptyState.kt`
- `feature/notifications/src/main/java/com/practicalchristian/app/feature/notifications/hub/NotificationsHubScreen.kt`
- `feature/schedules/src/main/java/com/practicalchristian/app/feature/schedules/SchedulesScreen.kt`
