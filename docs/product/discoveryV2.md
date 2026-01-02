# Bible Meditation App — Revised Discovery (v2)

> A comprehensive, privacy‑first, offline‑capable devotional platform that fosters unhurried, theologically grounded engagement with Scripture and community.

---

## 1) Executive Summary

This revision consolidates the original feature list and extends it with critical pillars required for a resilient, faithful product: (a) Scripture licensing and text integrity, (b) editorial/theological governance, (c) humane habit design, (d) safety and moderation for prayer/community, (e) privacy, data ownership and portability, (f) structured content operations, and (g) an offline‑first architecture. The app’s North Star is **Meaningful Time with Scripture per Week (MTS/W)**—quality of engagement over raw frequency.

---

## 2) Product Vision & North Star

**Vision:** Enable Christians worldwide to cultivate a sustainable rhythm of Scripture reading, meditation, prayer, and reflection—personally and with trusted small groups—rooted in sound doctrine and gentle guidance.

**North Star Metric:** **MTS/W — Meaningful Time with Scripture per Week**, defined as sessions ≥ 5 minutes with at least one reflective action (journal/prayer/highlight).

**Guiding Principles:**

* Sacred experience over growth hacks
* Privacy and agency over extractive engagement
* Clarity and charity in theology
* Offline‑first reliability

---

## 3) User Segments & Primary Use Cases

* **Daily Devotional Seekers** — want simple, guided meditations and reminders.
* **Plan‑Driven Readers** — want structured plans (Bible-in-a-year, thematic, seasonal).
* **Reflective Journalers** — want a private, searchable, exportable journal.
* **Small Group Members** — want shared plans, prayer lists, and weekly check‑ins.
* **Church Leaders** *(B2B)* — want to distribute sermon‑aligned plans and see aggregate engagement (privacy‑preserving).

---

## 4) Theology & Editorial Governance

* **Positioning:** Ecumenical baseline with **Tradition Profiles** (e.g., broadly evangelical, catholic, orthodox, mainline protestant) to tune commentary tone and suggested resources.
* **Editorial Board:** Human editorial approval for all devotional content; style and sensitivity guidelines (grief, trauma, doubt).
* **Seasonal Calendars:** Advent/Lent/Easter and regional observances; pre‑scheduled, localized content.

---

## 5) Scripture & Licensing Strategy

* **Translations:** Launch with at least one permissive translation (e.g., public‑domain) for offline; roadmap for licensed modern translations (rights determine offline caching, quotation limits, and audio).
* **Canonical IDs:** Adopt a standard (e.g., OSIS) for cross‑translation highlights, bookmarks, search and references.
* **Numbering Differences:** UI accommodates verse numbering differences (e.g., Psalms, Deuterocanon), clearly indicated.
* **Cross‑Reference Graph:** Curate or license a cross‑reference dataset to power related‑verse insights.

---

## 6) Core Experience (Session Archetypes)

* **Lectio Divina:** Read → Meditate → Pray → Contemplate; time‑boxed templates (5/10/20 mins).
* **Breath Prayer & Silence:** Short guidance, ambient timer, gentle close.
* **Gratitude Examen:** Prompted reflection on the day; optional journal and verse tie‑ins.
* **Verse Memorization (SRS):** Spaced repetition of saved verses.

---

## 7) Feature Set (Reframed & Expanded)

### 7.1 Daily Meditations

* Curated, theologically reviewed meditations with citations.
* **Theme/Thematic Collections** (forgiveness, hope, gratitude, lament, justice).
* Seasonal series (Advent/Lent) and local observances.

### 7.2 Personalization

* **Rule‑of‑Life Onboarding:** preferred time(s), Sabbath/rest day, desired session length, tradition profile, spiritual goals.
* Behavioral tuning of recommendations; **Sabbath Mode** to quiet notifications on chosen days.

### 7.3 Daily Quotes & Notifications

* Short Bible verses or vetted quotes with share sheets.
* **Humane delivery:** batch or digest options; avoid guilt‑inducing copy.

### 7.4 Bible Reading Plans

* Bible‑in‑a‑year, canonical/gospels/psalms, thematic, and challenge‑based plans.
* **Custom Plan Builder** with templates; import from church leaders.
* **Graceful Catch‑Up:** auto‑reschedule after missed days; freeze/skip options.

### 7.5 Progress Tracking & Habit Design

* Dashboard showing plan progress, streaks with **freeze windows**, and MTS/W.
* WakaTime‑inspired daily/weekly time charts.

### 7.6 Customization & Schedule Flexibility

* Pause plans, adjust cadence, choose preferred translation per plan.
* **Sabbath/Rest Day** and **Quiet Hours**.

### 7.7 Prayer Requests & Journaling

* Personal requests with statuses and reminders; private by default.
* **Journal (encrypted):** freeform notes, tags, verse links, mood logging (opt‑in).
* **Data Ownership:** export to Markdown/PDF; selective deletion.

### 7.8 Community Engagement (Safety‑First)

* **Small Groups / Circles:** private spaces to share plans and prayer updates.
* Privacy levels for requests: private → circle → church → public (opt‑in).
* **Moderation & Steward Roles:** reporting, escalation, and community guidelines. Stewards are trusted community members who facilitate healthy discussion, model positive engagement, and help resolve conflicts, whereas moderators focus on enforcing rules and handling violations.
* Sensitive‑content protocols (surface helplines/resources; do not store raw risk signals).

### 7.9 Reminders

* Time‑zone‑aware reminders, calendar‑aware nudge windows, catch‑up summaries.

### 7.10 In‑Built Bible (Multi‑Translation)

* Highlight, bookmark, copy/share with proper attribution; offline packs.

### 7.11 Search & Navigation

* Offline full‑text search (FTS), cross‑translation hopping, topical tags and indexes.
* Saved searches and collections (e.g., “Sermon Prep: Hope”).

### 7.12 Offline Access

* Downloadable language/translation packs; fully functional reading, journaling, and plan tracking offline.

### 7.13 Reflection Prompts & Insights

* Contextual prompts aligned to readings; journaling templates.

### 7.14 Gamification & Challenges (Gentle)

* Challenges engine for 7/14/30‑day themes; reflective, not performative badges.

### 7.15 AI‑Driven Guidance (Guardrailed)

* **RAG with Citations:** answers include explicit references (translation, commentary).
* **Tradition‑Aware:** interpretive variants presented neutrally, aligned to chosen profile.
* On‑device or privacy‑preserving NLP for journal insights (opt‑in).

### 7.16 Multilingual Support

* RTL languages, regional notes, downloadable packs, locale‑aware holidays.

### 7.17 Local Church & Community Integration (B2B)

#### 7.17.1 Church Membership Linking (Online)
Users can **search and select** their church from a verified directory or join via **invite code/QR**. Membership scopes content (sermons, bulletins, reading plans). Users may belong to multiple churches/campuses; one set as **Primary**.

#### 7.17.2 Pastor “Preachings” Distribution
Churches upload sermons/messages (audio/video/text) with metadata (series, scripture references, tags). Members receive **pastor-specific preachings** via a dedicated feed with optional push notifications (e.g., “New Sunday message available”).

#### 7.17.3 Fallback for Non‑Members
Users without a church receive a **general preaching channel** (curated, multi‑tradition aware). They may opt into suggested nearby churches (opt‑in location) or global partner channels.

#### 7.17.4 Sermon‑Synced Plans
Leaders attach weekly or seasonal reading plans + reflection prompts tied to the sermon; members can auto‑enroll.

#### 7.17.5 Local Event Locator & Bulletin
Moderated events, service times, group meetings; RSVP and private follow‑ups with reflective prompts.

#### 7.17.6 Pastoral Dashboard (Aggregate Only)
Plan participation and completion rates; engagement trends. **No access** to journals or personal prayer content.

#### 7.17.7 Rights & Moderation
Clear IP/license grant from churches; takedown & DMCA process; steward moderation for comments/prayer threads within church spaces.
### 7.18 Daily Bread–Style Online Devotionals

* **Daily Devotional Feed:** An always‑fresh, **cloud‑delivered** devotional (scripture + short reflection + prayer) available to all users. Content may be in‑house or from licensed partners; cached for offline.
* **Personalization:** The feed respects **Tradition Profile**, time of day, and spiritual goals from onboarding. Offers longer/shorter variants (5/10/20 minutes).
* **Citations & Sources:** Each devotional cites translation and commentary sources; seasonal variants (Advent/Lent).
* **Notifications & Digest:** Optional “Daily Bread” reminder or a morning/evening digest; respects Quiet Hours and Sabbath Mode.

---

## 8) Accessibility & Inclusivity

* Scalable typography, adjustable line length, dyslexia‑friendly settings, high‑contrast themes.
* Full screen‑reader coverage; focus order validated; haptics and audio captions.
* RTL layout, proper punctuation/diacritics handling.

---

## 9) Privacy, Security & Compliance

* GDPR‑grade consent, minimization, and clear purposes per data category.
* **Encrypted at rest**; optional biometric gate for journals/prayers.
* Data lifecycle: export (JSON/Markdown/PDF), selective or full deletion.
* Youth mode where applicable (parental consent; restricted sharing).

---

## 10) Content Operations & CMS Workflow

* Headless CMS with roles: Author → Editor → Theological Reviewer → L10n → QA → Publish.
* Versioning, scheduled release windows, translation management, Scripture‑reference validator.

---

## 11) Technical Architecture (High Level)

* **Offline‑First Core:** Local SQLite for Scripture, plans, progress, journals, prayer, groups; background sync with conflict resolution.
* **Canonical IDs:** OSIS‑like scheme across translations; cross‑reference and topical indices.
* **Search:** SQLite FTS5 (offline), server‑side indexing for global search suggestions; transliteration and fuzzy matching.
* **Sync & Identity:** Email/pass + federated sign‑in; per‑entity versioning; resumable, idempotent sync; encrypted in transit; optional E2E for journals/prayers.
* **Notifications:** Time‑zone aware, quiet hours, digest and catch‑up; server‑side scheduler.
* **Church Directory Service (Online):** Verified registry of churches/campuses; membership linking via invite codes, domain verification, or admin approval.
* **Content Ingestion Pipeline:** Sermon upload (audio/video/PDF/notes) → media storage (S3/GCS) → CDN delivery → optional **ASR transcription** → **summary & tagging** (AI assist, human review) → publish; scripture references auto‑linked.
* **Preaching/Devotional Delivery:** Topic channels (pastor, series, general) with subscription graph; server‑driven configuration of feed; offline caching.
* **AI Layer (Guardrailed):** RAG over licensed texts/commentaries with explicit citations; tradition‑aware variants; on‑device NLP for private insights where feasible.
* **Analytics & Experiments:** MTS/W, activation/retention cohorts; feature flags; server‑configured experiments.
* **Cross‑Platform:** Native iOS/Android with shared domain (KMP) or equivalent; web reader later.

---

## 12) Core Data Entities (ER Outline)

* **User, Profile, TraditionProfile**
* **Church, Campus, Pastor, Membership** (role, status, primary flag)
* **Translation, License, Book, Chapter, Verse** (canonical IDs)
* **Sermon/Preaching, SermonSeries, SermonMedia, SermonTranscript, SermonOutline, Channel, Subscription**
* **Plan, PlanItem, Progress, Streak**
* **Highlight, Bookmark, Note, JournalEntry** (encrypted)
* **PrayerRequest, PrayerUpdate, Reaction**
* **Group (Circle/Church), MembershipRole (steward/moderator)**
* **NotificationPreference, Reminder**
* **TopicTag, CrossReference**
* **ConsentRecord, AuditLog, RightsGrant**

---

## 13) Metrics & Research Program

* **North Star:** MTS/W
* **Activation:** Onboarded + first reading + one reflective action within 48h.
* **HEART/AARRR:** Happiness (NPS), Engagement (weekly active, session depth), Adoption (plan starts), Retention (W1/W4/W12), Task Success (plan completion).
* In‑app micro‑surveys; opt‑in interviews; tagged feedback on meditations.
* Experiment levers: notification timing, session lengths, SRS cadence, tradition profiles in recommendations.

---

## 14) Monetization & Pricing (Principled)

* **Freemium:** Scripture reading, one offline public‑domain translation, basics of journaling and plans.
* **Plus Subscription:** licensed translations, advanced search, SRS memorization, seasonal series, multi‑device sync, small groups.
* **Church Plan (B2B):** sermon‑synced plans, private groups, aggregate analytics, prioritized support.
* **No ads** within Scripture/prayer flows; if ever used, limited to peripheral surfaces.

---

## 15) Roadmap

**MVP (3–4 months)**

* Rule‑of‑Life onboarding; Daily meditations (in‑house) + **Daily Devotional Feed (online)**
* One offline public‑domain translation; reader with highlight/bookmark/share
* Reading plans (Bible‑in‑a‑year + 2 thematic), catch‑up & freeze
* Journal (encrypted), personal prayer requests, reminders
* Offline FTS search; seed cross‑refs
* Humane streaks; Sabbath Mode; export & GDPR controls
* Accessibility baseline; analytics (MTS/W, activation & retention)
* **Basic Church Membership & Preaching Feed:** join a church via invite/search; receive pastor preachings (audio/text) with push; **general preaching channel** for non‑members

**v1.1**

* Verse memorization (SRS), saved collections
* Small Groups (private circles) with shared plans and sermon discussion prompts
* Tradition profiles (lightweight), seasonal calendars
* AI Q&A with RAG + citations; on‑device insights (opt‑in)
* **Church Admin Console:** sermon uploads, rights grant, scheduling; basic aggregate analytics

**v2**

* Audio Bibles (licensed/TTS) with verse‑synced captions
* Church Plan (B2B): bulletin, event management, richer aggregate analytics
* Marketplace for vetted devotional series and partner devotionals
* Advanced search (fuzzy/phonetic), multilingual packs, web reader

---

## 16) Risks & Mitigations

* **Licensing constraints:** Engage early; stage translations; clear budget.
* **Theological drift via AI:** RAG‑only answers with citations; human editorial authority.
* **Community harm:** Private‑by‑default, small groups, moderation tools, sensitive‑content protocols.
* **Privacy breach:** Encryption, minimal scopes, fine‑grained consent, regular audits.
* **Engagement guilt:** Grace windows, streak freezes, Sabbath Mode, compassionate copy.

---

## 17) Open Decisions

1. Initial translation(s) and licensing budget; offline rights needed at MVP?
2. Tradition profiles: which to include at launch and how deep?
3. Editorial board composition and service‑level for content throughput.
4. Church Plan scope for pilot churches; pricing bands.
5. Audio strategy (licensed vs. TTS) and timing.

---

## 18) MVP Acceptance Criteria (Condensed)

* **Onboarding:** Users set time, session length, Sabbath day, tradition; preferences inform recommendations.
* **Daily Devotional Feed (Online):** A cloud‑delivered devotional of the day is visible, cites sources, and is cached for offline; users can enable/disable reminders.
* **Reader:** Smooth navigation; accurate verse rendering; highlights/bookmarks persist offline.
* **Plans:** Start, pause, resume, catch‑up; progress shown; streak freeze available.
* **Journal & Prayer:** Encrypted local storage; create/edit/delete; reminders; export works.
* **Search:** Offline FTS across Scripture, highlights, and notes; results open correct verse.
* **Notifications:** Time‑zone aware; digest and catch‑up; respect quiet hours/Sabbath.
* **Church Membership & Preachings:** Users can find or join a church (invite/search). Members receive new pastor preachings as a feed + push. Non‑members see a general preaching channel. Rights metadata present; playback/reading works; content can be cached for offline.
* **Privacy:** Consent surfaces; data export and account deletion succeed.
* **Accessibility:** Screen‑reader usable; adjustable typography; high contrast.
* **Analytics:** MTS/W captured; activation/retention cohorts visible.

---

## 19) Appendix: Example Tradition Profile Effects (Illustrative)

* **Evangelical:** commentary sources A/B; default reading order emphasizes Gospels & Pauline epistles.
* **Catholic:** includes deuterocanonical options; references to Catechism; liturgical calendar prompts.
* **Orthodox:** Septuagint numbering considerations; liturgical readings emphasis.
* **Mainline Protestant:** balanced OT/NT rotation; inclusive language guidance.

> Profiles are opt‑in, transparent, and always cite sources; users may switch at any time.
