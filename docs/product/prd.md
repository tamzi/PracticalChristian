# PracticalChristian product requirements

## Purpose

This document defines the user outcomes and product requirements for
PracticalChristian. It does not define technical implementation details or a
separate release roadmap.

Use the product documents in this order:

1. `docs/product/discoveryV2.md` defines product strategy, build sequencing,
   gating decisions, and market-ready launch criteria.
2. This PRD defines required behavior and product boundaries.
3. `docs/product/featureList.md` maps capabilities to build phases and launch
   requirements.
4. `docs/product/competitorAnalysis.md` provides directional market research.

Technical decisions belong in `docs/tech/technicalArchitecture.md` and
`docs/tech/adr.md`.

## Product statement

PracticalChristian is an Android-first Bible meditation app for people who want
a sustainable rhythm of Scripture reading, prayer, reflection, and community.
It combines an offline Bible reader, guided devotional sessions, reading plans,
private journaling, prayer tracking, verse memorization, and trusted church
content.

The product should help users spend meaningful time with Scripture without
turning spiritual practice into a guilt-driven engagement loop.

## Product goals

- Make daily Scripture engagement easy to begin and meaningful to continue.
- Support reading, listening, prayer, reflection, and memorization in one flow.
- Keep core reading and personal reflection usable without a network connection.
- Protect journals, prayers, church membership, and other sensitive data.
- Give churches a controlled way to extend Sunday teaching into weekday
  practice.
- Serve multiple Christian traditions without hiding the source or perspective
  of commentary.

## Non-goals

- Replace a local church or pastoral care.
- Present AI output as spiritual authority.
- Build a public social network centered on follower counts or viral content.
- Measure spiritual maturity from private user activity.
- Lock basic access to Scripture behind a subscription.
- Launch iOS and web clients before the Android product meets the market-ready
  criteria in `docs/product/discoveryV2.md`.

## Platform and release direction

The first market-ready product is Android. The current codebase uses Android
libraries and preserves domain boundaries that can support a later Kotlin
Multiplatform transition.

Development follows the dependency-ordered phases in
`docs/product/discoveryV2.md`. Those phases are build sequencing, not separate
public product releases. Market readiness requires the complete launch criteria
in that document.

## Audience

### Daily devotional seekers

People who want a short, guided way to read Scripture, reflect, and pray each
day.

### Plan-driven readers

People who prefer structured reading plans, visible progress, and flexible
scheduling.

### Reflective journalers

People who want a private place to record prayers, questions, notes, and
Scripture-linked reflections.

### Small groups

Trusted groups that want shared plans, prayer updates, and weekly discussion
without using a public feed.

### Church leaders

Pastors and ministry teams that want to publish sermon-linked content and view
privacy-preserving aggregate engagement.

## Product principles

### Scripture first

Every devotional, plan, prompt, and AI-assisted answer should point users back
to Scripture and identify its sources.

### Private by default

Personal journals, prayer entries, reading activity, and church membership are
not shared unless the user makes a clear choice to share them.

### Humane habits

Reminders, progress, streaks, and challenges should support consistency without
punishing missed days. Quiet Hours, Sabbath Mode, catch-up, pause, and freeze
controls are required.

### Offline reliability

Reading, journaling, prayer tracking, saved plans, and progress must remain
useful offline. Sync should recover without data loss when connectivity returns.

### Theological clarity

Official content requires human editorial review. Tradition-aware material must
identify its perspective and cite the Scripture translation or commentary used.

## Core journeys

### Complete a daily devotional

1. The user opens the app and sees the current devotional.
2. The user chooses an available session length.
3. The user reads or listens to the Scripture and reflection.
4. The user can pray, journal, highlight, or save a verse.
5. The user marks the session complete or resumes it later.

### Follow a reading plan

1. The user chooses a plan and schedule.
2. Plan content is available offline after enrollment or download.
3. The user completes readings at their own pace.
4. Missed days can be rescheduled, skipped, or completed later.
5. Progress reflects completed work without resetting spiritual practice to
   zero.

### Record a private reflection

1. The user opens the journal from a devotional, verse, prayer, or journal list.
2. Relevant Scripture references are attached when available.
3. The entry is stored locally with the required encryption boundary.
4. The entry syncs only when secure sync is enabled.
5. The user can search, export, edit, or delete the entry.

### Join a trusted group

1. The user joins through an invitation, approved church space, or group code.
2. The group clearly shows its owner, stewards, privacy level, and rules.
3. Members can share plan reflections and prayer updates.
4. Reporting, moderation, removal, and notification controls are available.

### Follow church content

1. The user finds or joins a verified church or campus.
2. The user chooses whether it is their primary church.
3. Sermons, sermon-linked plans, and church devotionals appear in a dedicated
   feed.
4. The user controls notifications and participation.
5. Church leaders receive aggregate engagement only. They cannot access private
   journals or personal prayers.

## Functional requirements

### Onboarding and identity

- Users can explore the core local experience before creating an account.
- Account creation supports secure backup, sync, community, and church features.
- Onboarding captures session length, reminder preferences, Sabbath or rest day,
  spiritual goals, preferred translation, and optional tradition profile.
- Users can change onboarding choices later.
- Account deletion removes associated cloud data according to the documented
  retention policy.

### Daily devotional sessions

- The app provides source-cited devotional content with Scripture, reflection,
  and prayer.
- Sessions support 5, 10, and 20 minute formats where content is available.
- Users can read, listen, pause, resume, and complete a session.
- Completed sessions can prompt journaling or prayer without blocking exit.
- Current and recently opened sessions are cached for offline use.

### Bible reader

- At least one permitted translation is available offline at launch.
- Users can navigate by book, chapter, verse, reference, and search.
- Users can highlight, bookmark, copy, share, and attach verses to notes.
- Translation attribution and quotation limits are enforced.
- Canonical verse identifiers preserve links across supported translations and
  account for numbering differences.

### Reading plans

- Users can browse, search, start, pause, resume, and complete plans.
- Plans support flexible cadence, catch-up, skip, and rest days.
- Progress remains available offline and syncs when connectivity returns.
- Plans can be private, shared with a circle, or published by a verified church.
- Custom plan creation follows the content and rights rules defined in
  `docs/product/discoveryV2.md`.

### Journal and prayer

- Journal entries support freeform text, tags, dates, and Scripture links.
- Prayer requests support status, reminders, updates, and answered-prayer
  history.
- Personal entries are private by default.
- Users can export and selectively delete their data.
- Search must not weaken the chosen encryption boundary.
- Any automated journal insight is opt-in, privacy-preserving, and never used to
  infer spiritual maturity.

### Verse memorization

- Users can add verses from supported translations.
- Reviews use spaced repetition and work offline.
- Users can pause a verse without losing its history.
- Progress should emphasize practice rather than public comparison.

### Search

- Search works offline for downloaded Scripture and supported personal content.
- Results identify content type, translation, and source.
- Opening a result returns the user to the correct verse, plan item, journal
  entry, or devotional.

### Audio

- Supported Scripture and devotional text can be read aloud.
- Playback includes pause, resume, speed, and background controls.
- Recorded audio identifies rights, source, language, and download availability.
- Audio-only content includes transcripts or equivalent text.

### Notifications

- Users control devotional, plan, memorization, prayer, group, and church
  notifications separately.
- Notifications respect time zone, Quiet Hours, and Sabbath Mode.
- Re-engagement language must not shame users for inactivity.
- Marketing notifications are separate from spiritual-practice reminders.

### Circles and community

- Circles are private by default and require a clear joining action.
- Members can share plan progress, reflections, and prayer updates according to
  the selected privacy level.
- Group owners and stewards can moderate content and membership.
- Users can report content, mute a circle, leave a circle, and block another
  user.
- Sensitive-content protocols surface appropriate external help without storing
  raw risk classifications.

### Church platform

- Churches and campuses require verification before public discovery.
- Users can belong to multiple churches and choose one primary church.
- Leaders can publish sermons, devotionals, plans, events, and announcements
  when they hold the required rights.
- Sermon-linked content identifies its church, author, Scripture references, and
  publication status.
- Church analytics are aggregate and exclude private journals, private prayers,
  and private reading details.

### AI-assisted guidance

- AI answers use approved Scripture and commentary sources.
- Answers show citations and distinguish source material from generated
  explanation.
- Major interpretive differences are presented clearly when relevant.
- AI does not claim divine authority, diagnose users, or replace pastoral,
  medical, legal, or emergency support.
- AI-generated devotional material requires human review before publication.

## Content requirements

- Official devotional content follows the workflow in
  `docs/product/discoveryV2.md`.
- Every published item records author, reviewer, Scripture references,
  translation, rights, locale, and version.
- Content involving grief, trauma, abuse, self-harm, or crisis follows a
  dedicated sensitivity review.
- Licensed content cannot be exported, copied, cached, or shared beyond its
  contract terms.
- Churches grant explicit rights for uploaded sermons and derivative content.

## Privacy and security requirements

- Collect only data required for the selected features.
- Keep private content encrypted at rest and in transit.
- Resolve the end-to-end encryption and recovery model before building cloud
  journal or prayer search.
- Keep analytics events free of journal text, prayer text, message content, and
  precise Scripture-reading history unless the user gives specific consent.
- Provide data export, selective deletion, full account deletion, and consent
  withdrawal.
- Treat youth access, church membership, prayer sharing, and location as
  high-sensitivity product areas.

## Accessibility requirements

- Support screen readers, predictable focus order, scalable typography, high
  contrast, and large touch targets.
- Do not rely on color, sound, gestures, or animation as the only signal.
- Provide text alternatives for audio and video.
- Support right-to-left layout and locale-appropriate punctuation for launch
  languages.
- Core journeys must remain usable with reduced motion and assistive input.

## Success measures

The North Star metric is Meaningful Time with Scripture per Week, defined in
`docs/product/discoveryV2.md`.

Supporting measures include:

- Activation: onboarding, first Scripture session, and one reflective action.
- Retention: weekly and monthly return rates without relying on guilt-based
  prompts.
- Session completion by chosen session length.
- Reading-plan continuation and completion.
- Use of journal, prayer, and memorization features.
- Circle and church participation without privacy or moderation incidents.
- Content quality, accessibility, reliability, and user-reported trust.

Private text and inferred spiritual condition are not success metrics.

## Monetization boundaries

- Basic Scripture reading, one permitted offline translation, and core personal
  reflection remain available without ads.
- Paid plans may fund licensed translations, premium audio, partner content,
  advanced sync, or church administration.
- Pricing and entitlements must reflect licensing and infrastructure costs.
- Payment status must not change the privacy protections applied to user data.
- Product copy should explain what a payment funds without implying that
  spiritual growth is a paid benefit.

## Open decisions

The unresolved product decisions are maintained in
`docs/product/discoveryV2.md`. They cover:

- Translation licensing and launch rights.
- Tradition-profile depth.
- Backend and sync platform.
- Audio sourcing and timing.
- Editorial capacity and content cadence.
- Church platform scope and pricing.

Do not settle these decisions indirectly in feature copy, technical plans, or
task documents. Record the decision in the canonical discovery document and the
relevant architecture decision record first.
