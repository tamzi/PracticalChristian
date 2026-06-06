# Product feature inventory

This matrix summarizes product scope. Requirements live in
`docs/product/prd.md`; phase definitions and launch criteria live in
`docs/product/discoveryV2.md`.

| Capability                      | Build phase | Market-ready requirement | Key constraint                         |
| ------------------------------- | ----------: | ------------------------ | -------------------------------------- |
| Rule-of-Life onboarding         |           2 | Yes                      | Preferences remain editable            |
| Daily devotional feed           |           2 | Yes                      | Source-cited and cached offline        |
| 5/10/20 minute sessions         |           2 | Yes                      | Content must fit the stated duration   |
| Offline Bible reader            |           2 | Yes                      | Launch translation rights required     |
| Highlights and bookmarks        |           2 | Yes                      | Canonical verse identifiers            |
| Scripture and personal search   |           2 | Yes                      | Search must respect encryption         |
| Reading plans                   |           2 | Yes                      | Pause, resume, catch-up, and rest days |
| Custom plan builder             |           2 | Yes                      | Rights and editorial controls          |
| Encrypted journal               |           2 | Yes                      | Export and selective deletion          |
| Personal prayer tracking        |           2 | Yes                      | Private by default                     |
| Verse memorization              |           2 | Yes                      | Offline spaced repetition              |
| Notifications and reminders     |           2 | Yes                      | Quiet Hours and Sabbath Mode           |
| Progress and MTS/W              |           2 | Yes                      | No guilt-driven scoring                |
| Text-to-speech playback         |           6 | Yes                      | Text alternative remains available     |
| Licensed recorded audio         |           6 | No                       | Rights and download rules              |
| Tradition profiles              |           3 | Yes                      | Transparent and source-cited           |
| Cross-reference graph           |           3 | Yes                      | Licensed or curated source             |
| AI Scripture Q&A                |           3 | Yes                      | Citations, guardrails, human authority |
| Private circles                 |           4 | Yes                      | Reporting and moderation               |
| Shared plans and prayer updates |           4 | Yes                      | Explicit audience selection            |
| Verified church directory       |           5 | Yes                      | Verification and takedown process      |
| Pastor preaching feed           |           5 | Yes                      | Rights metadata and offline cache      |
| Sermon-linked plans             |           5 | Yes                      | Human review before publication        |
| Church events and bulletin      |           5 | Yes                      | Moderated publishing                   |
| Aggregate pastoral analytics    |           5 | Yes                      | No private journal or prayer access    |
| Multilingual packs              |           6 | Yes                      | Launch locales must pass RTL checks    |
| Web reader                      |           6 | No                       | Follows Android market readiness       |
| iOS client                      | Post-launch | No                       | Uses proven shared-domain boundaries   |
| Partner content marketplace     |           6 | No                       | Editorial, rights, and payout controls |

## Deferred or excluded

- Public follower-based social feeds.
- Unreviewed AI-generated devotionals.
- Automated spiritual-maturity scores.
- AI analysis of private journals without explicit opt-in and a
  privacy-preserving implementation.
- Wearable and voice-assistant clients before the core product is stable.
