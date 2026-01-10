# Scripts

Utility scripts for the PracticalChristian project.

## Git Hooks

### Quick Start

**🎉 Automatic Installation**

Git hooks are **automatically installed** during your first Gradle sync!

**Manual Installation (if needed):**

From repo root (all hooks):
```bash
./scripts/install-git-hooks.sh
```

From repo root (pre-commit only):
```bash
./scripts/install-hooks.sh
```

From this `scripts/` directory:
```bash
./install-git-hooks.sh
```

Click to open scripts:
- `install-git-hooks.sh` -> [install-git-hooks.sh](./install-git-hooks.sh)
- `install-hooks.sh` -> [install-hooks.sh](./install-hooks.sh)

**Verify hooks are installed:**

```bash
./scripts/verify-hooks.sh
```

### Pre-Commit Hook

Runs **fast validation checks** before every commit (< 5 seconds):
- File count and naming conventions
- Documentation rules enforcement
- No code examples in markdown files
- Design system enforcement (no Material components or raw colors)

### Commit-Msg Hook

Validates commit message format.

### Pre-Push Hook

Runs comprehensive checks before pushing:
- **Phase 1:** Validates commit history
- **Phase 2:** Runs `./gradlew detekt` and `./gradlew test`

This separation ensures commits are fast while maintaining code quality.

### Bypassing Hooks
**Not recommended**
See `docs/agentRules/commitRules.md` for the policy on bypassing hooks.

### Available Scripts

| Script                    | Purpose                                         |
|---------------------------|-------------------------------------------------|
| `auto-install-hooks.sh`   | Auto-installs hooks if missing                  |
| `install-git-hooks.sh`    | Installs pre-commit, commit-msg, and pre-push   |
| `install-hooks.sh`        | Installs pre-commit only                        |
| `verify-hooks.sh`         | Verifies hooks are properly installed           |
| `check-hooks-on-build.sh` | Checks hooks during build                       |
| `check-design-system-usage.sh` | Blocks Material usage and raw colors     |
| `pre-commit-hook.sh`      | Validates staged changes                        |
| `commit-msg-hook.sh`      | Validates commit message format                 |
| `pre-push.sh`             | Validates commit history and runs tests         |
| `pre-receive.sh`          | Server-side validation (admin only)             |
| `setup-git-aliases.sh`    | Sets up convenient git aliases                  |

### Troubleshooting

**Hooks not running?**

1. Check: `./scripts/verify-hooks.sh`
2. Reinstall: `./scripts/install-git-hooks.sh` (or `./scripts/install-hooks.sh`)
3. Check path: `git config --get core.hooksPath`

**Team bypassing hooks?** See `docs/agentRules/commitRules.md`.
