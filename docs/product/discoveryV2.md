# PracticalChristian product discovery and strategy

> The canonical strategy for the PracticalChristian product.

## Document authority

This document owns:

- Product vision and differentiation.
- Build sequencing.
- Gating decisions.
- Product risks.
- Market-ready launch criteria.

`docs/product/prd.md` translates this strategy into requirements.
`docs/product/featureList.md` provides traceability. Neither document overrides
this strategy.

Architecture belongs in `docs/tech/technicalArchitecture.md` and
`docs/tech/adr.md`.

## Product vision

PracticalChristian helps people build a sustainable rhythm of Scripture
reading, meditation, prayer, reflection, and trusted community.

The product should feel calm, grounded, and useful on an ordinary day. It should
work when the user has five minutes, limited connectivity, or no interest in a
public social experience.

## North Star

The North Star is **Meaningful Time with Scripture per Week (MTS/W)**.

A meaningful session lasts at least five minutes and includes direct engagement
with Scripture plus one reflective action, such as prayer, journaling,
highlighting, memorization, or a plan response.

MTS/W is not a spiritual score. It is a product measure used to test whether the
app helps users spend more intentional time with Scripture.

## Audience

### Daily devotional seekers

They want a simple, guided session and a reminder that does not create guilt.

### Plan-driven readers

They want structured plans, visible progress, and room to pause or catch up.

### Reflective journalers

They want a private, searchable, exportable place for prayers and notes.

### Small groups

They want shared plans, prayer updates, and weekly discussion in a trusted
space.

### Church leaders

They want to distribute sermon-linked content and understand aggregate
participation without seeing private spiritual activity.

## Product wedge

The product combines five strengths that competitors usually separate:

1. An offline-first Scripture and devotional experience.
2. Private formation history through journaling, prayer, and memorization.
3. Humane habit support with Sabbath Mode, catch-up, and quiet hours.
4. Local church content that connects Sunday teaching to weekday practice.
5. Source-cited, tradition-aware guidance with human editorial authority.

The local church layer is the main distribution advantage. It should deepen the
personal devotional experience, not turn the app into church-management
software.

## Product principles

- Scripture before engagement mechanics.
- Privacy and user agency before data collection.
- Human editorial authority before automated publishing.
- Graceful habit support before streak pressure.
- Small, trusted communities before public feeds.
- Offline reliability before feature breadth.
- Android-first delivery with KMP-ready domain boundaries.
- Clear sources and perspectives before claims of theological neutrality.

## Product system

### Scripture and devotional practice

- Offline Bible reader with licensed translation controls.
- Source-cited daily devotionals.
- Guided 5, 10, and 20 minute sessions.
- Reading plans with pause, catch-up, skip, and rest days.
- Search, highlights, bookmarks, and Scripture-linked notes.
- Text-to-speech and licensed audio where rights allow.

### Personal formation

- Encrypted journal.
- Personal prayer tracking and reminders.
- Verse memorization with spaced repetition.
- Rule-of-Life onboarding.
- Quiet Hours and Sabbath Mode.
- Progress views centered on MTS/W rather than public comparison.

### Trusted community

- Private circles.
- Shared plans and prayer updates.
- Steward and moderator roles.
- Reporting, blocking, muting, and removal controls.
- Explicit audience selection for every shared prayer or reflection.

### Church platform

- Verified church and campus directory.
- Multiple memberships with one optional primary church.
- Sermon and devotional feed.
- Sermon-linked plans and reflection prompts.
- Events and bulletin content.
- Aggregate pastoral analytics.
- Rights, moderation, and takedown workflows.

### Guidance and personalization

- Optional tradition profiles.
- Passage-aware related content.
- Source-cited Scripture Q&A.
- Human review for published AI-assisted content.
- Opt-in, privacy-preserving personal insights.

## Theology and editorial governance

The product uses an ecumenical baseline for global content. Tradition profiles
may tune commentary, calendars, reading order, and suggested resources.

Profiles must be:

- Optional.
- Transparent.
- Easy to change.
- Clear about source and perspective.

Official devotional content follows this workflow:

1. Author.
2. Editor.
3. Theological reviewer.
4. Localization.
5. Quality and sensitivity review.
6. Publish.

Content records its author, reviewers, Scripture references, translation,
rights, locale, and version.

The review process needs dedicated guidance for grief, trauma, abuse,
self-harm, doubt, and denominational disagreement.

## Scripture and licensing

Launch translations depend on explicit rights for:

- Offline storage.
- Quotation and sharing.
- Search indexing.
- Audio.
- Export.
- Geographic distribution.

The product uses canonical verse identifiers so highlights, notes, plans, and
sermon references survive translation changes. The reader must handle canon and
verse-numbering differences without hiding them from users.

At least one permitted translation must remain available offline without a
subscription.

## Privacy and safety

Personal journals and prayers are private by default. The encryption and
recovery boundary must be decided before cloud sync or search is built around
it.

The product must provide:

- Data minimization.
- Encryption at rest and in transit.
- Explicit sharing choices.
- Export and selective deletion.
- Full account deletion.
- Consent withdrawal.
- Youth protections where applicable.
- Sensitive-content response guidance.

Analytics must not contain journal text, prayer text, group-message content, or
precise private reading history without specific consent.

Church leaders cannot access private journals, private prayers, or individual
reading details.

## Accessibility and internationalization

Accessibility is a launch requirement, not a later polish phase.

The product supports:

- Screen readers and predictable focus order.
- Scalable typography and large touch targets.
- High contrast and reduced motion.
- Alternatives to gesture-only actions.
- Text alternatives for audio and video.
- Right-to-left layouts.
- Locale-aware punctuation and calendars.

## Metrics and research

### Core measures

- MTS/W.
- Activation within 48 hours.
- Weekly and monthly retention.
- Session completion by selected duration.
- Plan continuation and completion.
- Journal, prayer, and memorization adoption.
- Circle and church participation.
- Reliability, accessibility, safety, and trust signals.

### Activation

An activated user completes onboarding, opens Scripture, and takes one
reflective action within 48 hours.

### Research program

- Short in-app surveys.
- Opt-in interviews.
- Tagged content feedback.
- Usability tests with assistive technology.
- Pilot studies with churches and small groups.
- Cohort analysis for reminders, session lengths, and Sabbath settings.

Private text and inferred spiritual condition are not product metrics.

## Monetization boundaries

The free product includes basic Scripture reading, one permitted offline
translation, and core personal reflection without ads.

Paid offerings may include:

- Licensed translations.
- Premium audio.
- Partner devotional series.
- Advanced sync or search.
- Church administration and aggregate analytics.

Payment never weakens privacy protections. Product copy should explain what a
payment funds without implying that spiritual growth is a paid benefit.

## Build plan

The first market-ready client is Android. Development is sequenced by
dependency, not by separate public releases. A later phase cannot ship ahead of
the foundation it depends on.

### Phase 0: Gating decisions

Resolve the decisions in the Gating decisions section before dependent work
starts.

### Phase 1: Foundations

- Canonical Scripture identifiers.
- Backend, identity, and offline-sync contracts.
- Encryption and recovery model.
- Content pipeline and editorial workflow.
- Analytics event schema.
- Sacrament design-system and accessibility baseline.
- Android implementation with KMP-ready domain boundaries where practical.

### Phase 2: Core devotional experience

- Bible reader and offline translation packs.
- Daily devotional sessions.
- Reading plans and flexible scheduling.
- Journal and personal prayer tracking.
- Verse memorization.
- Offline search.
- Notifications, Quiet Hours, and Sabbath Mode.
- MTS/W and private progress views.

### Phase 3: Guidance and personalization

- Tradition profiles.
- Cross-reference graph.
- Passage-aware recommendations.
- Source-cited Scripture Q&A.
- Opt-in, privacy-preserving journal insights.

### Phase 4: Community

- Private circles.
- Shared plans and prayer updates.
- Steward and moderator roles.
- Reporting, blocking, muting, and escalation.
- Sensitive-content response flows.

### Phase 5: Church platform

- Verified church directory.
- Membership by search, invitation, or QR code.
- Sermon and devotional ingestion.
- Sermon-linked plans.
- Church events and bulletin.
- Aggregate pastoral analytics.
- Rights, moderation, and takedown controls.

### Phase 6: Reach and richness

- Licensed or text-to-speech audio.
- Launch-language packs and right-to-left support.
- Partner devotional series.
- Web reader after Android market readiness.

iOS follows the proven Android product. It is not a condition for the first
market-ready release.

## Risks

### Licensing delay

Translation, audio, and commentary rights can block reader, search, sharing,
and monetization work. Rights must be settled before dependent implementation.

### Content supply

A daily product needs a reliable editorial cadence. The content pipeline and
staffing model must exist before launch.

### Foundation rework

Canonical identifiers, sync, and encryption are expensive to retrofit. They
belong in Phase 1.

### Theological drift

Automated guidance can flatten or invent interpretations. Approved sources,
visible citations, and human editorial authority are mandatory.

### Community harm

Prayer and faith discussions can expose vulnerable users. Private defaults,
moderation tools, and response protocols are required before community launch.

### Privacy breach

Journals, prayers, church membership, and location are sensitive. Minimize
collection and test access controls as product behavior, not policy text.

### Two-product load

The personal devotional app and church platform have different users and
operational needs. Resource them separately so the church platform does not
block the personal experience.

### Cost at scale

Media delivery, transcription, AI inference, sync, and storage have marginal
cost. Model cost per active user and paid tier before broad rollout.

### Engagement guilt

Streaks and reminders can turn spiritual practice into pressure. Grace windows,
Sabbath Mode, pause, and compassionate copy are required.

## Gating decisions

These decisions block later work:

1. Which translations launch, and what offline, quotation, search, export, and
   audio rights are secured?
2. Which tradition profiles launch, and how much do they affect content?
3. Which managed or self-hosted backend supports sync, church content, and AI?
4. Is launch audio text-to-speech, licensed narration, or both?
5. Who owns editorial approval, and what content throughput can they sustain?
6. What is the church-plan scope, pilot model, and pricing boundary?

Record resolved product decisions here and implementation consequences in
`docs/tech/adr.md`.

## Market-ready launch criteria

The Android product is market-ready when all of the following are true:

- Onboarding choices affect the experience and remain editable.
- Supported translations render accurately and work offline.
- Highlights and bookmarks persist offline and sync without data loss.
- Daily sessions support the promised duration, sources, and offline cache.
- Plans support start, pause, resume, catch-up, skip, and rest days.
- Journal and prayer data can be created, edited, exported, and deleted.
- Search opens the correct Scripture or personal-content result.
- Notifications respect time zone, Quiet Hours, and Sabbath Mode.
- Audio has playback controls and a text equivalent.
- Tradition profiles are transparent and source-cited.
- AI answers include citations and remain inside approved guardrails.
- Circles enforce privacy, membership, reporting, and moderation.
- Church content carries verification, rights, and publication metadata.
- Pastoral analytics remain aggregate.
- Consent, export, deletion, and youth protections work end to end.
- Screen-reader, scalable-text, high-contrast, reduced-motion, and
  right-to-left checks pass for launch locales.
- MTS/W and activation events match the approved analytics schema.
- Core reading, planning, journaling, and prayer work offline.
- Sync conflicts resolve without silent data loss.

## Post-launch expansion

- iOS client.
- Additional translations and languages.
- More licensed audio.
- Web reader improvements.
- Vetted partner marketplace.
- Wearable and voice-assistant experiences.

Post-launch work must not weaken the product principles or privacy boundaries.
