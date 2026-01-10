# Comprehensive Audit Trails

## Purpose

Define the architecture and operational approach for audit trails that capture critical actions
across the app for security analysis and compliance, without exposing logs to end users.

## Goals

- Record critical actions with enough context to support compliance reviews.
- Persist locally using Room with encryption at rest.
- Capture both attempt and outcome events with correlation IDs.
- Retain logs for a fixed time window (90 days).
- Sync logs to a backend when network is available.
- Keep audit collection non-blocking and invisible to users.

## Non-Goals

- No in-app UI for viewing or exporting audit logs.
- No user-triggered upload or sharing.

## Critical Action Criteria (Proposed)

An action is critical if it:

- Changes authentication or authorization state.
- Changes security or privacy settings (permissions, encryption, data sharing).
- Creates, updates, or deletes user content that can affect compliance or recovery.
- Exports, syncs, or shares user data outside the device.
- Alters billing, subscription, or entitlement state.
- Modifies system configuration or sensitive profile data.
- Performs background sync that could expose or transmit user data.

Each feature should map its actions to these criteria and register audit events accordingly.

## Audit Event Fields

Minimum fields for each audit event:

- Event ID (unique identifier)
- Correlation ID (links attempt and outcome)
- User ID (signed-in user identifier)
- Action type (category + name)
- Action source (feature/screen/process)
- Target subject (entity type + ID, when relevant)
- Reason or trigger (user action, system trigger, scheduled)
- Device context (device model, OS version, app version)
- Network context (online/offline state)
- Permission state (OS permission status when relevant)
- Outcome (attempt, success, failure)
- Failure reason (if failure)
- Timestamp (UTC)
- Metadata (key-value extras, minimal and redacted)

## User Identity Strategy (Proposed)

- Use the signed-in user ID as the canonical identifier once authentication exists.
- Introduce a domain-level audit identity provider to avoid direct feature coupling.
- Until authentication is implemented, persist user ID as unknown and include an app-instance
  identifier in device context for correlation.

## Backend Ingestion Contract (Proposed)

- Upload events in batches with a fixed schema version.
- Each event is immutable and deduplicated server-side by event ID.
- Support partial acceptance with per-event status in the response.
- Use idempotency across retries to avoid duplication.
- Auth for ingestion should be service-token based (not end-user tokens).

## High-Level Flow

- Feature or domain layer triggers an audit event.
- Audit logger creates an event pair:
  - Attempt event
  - Outcome event (success or failure)
- Events are stored locally in Room with encryption at rest.
- Retention job prunes events older than 90 days.
- Upload worker sends pending events when network is available.
- Backend ingestion stores events for compliance access.

## Tech Diagram

```text
[Feature/UI/Domain]
         |
         v
   [Audit Logger]
         |
         v
[Local Audit Queue]
  (Room + SQLCipher)
         |
    +----+----+
    |         |
    v         v
[Retention] [Upload Worker]
  (90 days)   (network)
                   |
                   v
          [Backend Audit API]
                   |
                   v
       [Compliance Access Tools]
```

## Storage and Encryption

- Local persistence uses Room with SQLCipher for encryption at rest.
- Encryption keys are stored in Android Keystore and rotated per security policy.
- Audit metadata must be minimized and redacted to avoid unnecessary PII.

## Key Management and Rotation (Proposed)

- Use a versioned key alias to support rotation without breaking access.
- Rotate on a fixed cadence and on security incidents or key compromise.
- Re-encrypt the audit database during rotation; keep the prior key until migration completes.
- If keystore data is cleared, recreate the encrypted audit store and record a storage reset event.

## Retention

- Fixed retention window: 90 days.
- Enforce via periodic background cleanup and on startup when feasible.
- Cleanup should be resilient and not block app startup.

## Sync and Delivery

- Use a local-only queue with a stubbed backend interface.
- Upload worker only runs when the device is online.
- Use retry with backoff and idempotency keys to avoid duplication.
- Failed uploads remain in the queue for later retry.

## Compliance Access

- Compliance access is handled via the backend.
- No end-user access or export from the app.
- Backend should provide secure, auditable access pathways.

## Known Gaps

- Notification permission state changes are persisted without audit events. Add attempt and outcome
  events with user/context, timestamp, and outcome for the notification reminder flow in
  `feature/notifications/src/main/java/com/practicalchristian/app/feature/notifications/reminder/NotificationReminderViewModel.kt`.

## Open Decisions

- Establish the critical action catalog by feature.
- Confirm the audit identity provider location and fallback behavior.
- Finalize backend ingestion endpoint, auth, and retention requirements.
- Confirm rotation cadence and incident response workflow.
