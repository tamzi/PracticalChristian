# Detekt Setup Guide

## Overview

Detekt is now configured for static code analysis across all Kotlin modules in the PracticalChristian Android project.

## Running Detekt

### Analyze all modules
```bash
./gradlew detekt
```

### Analyze specific module
```bash
./gradlew :app:detekt
./gradlew :feature:presentation:detekt
```

### Create baseline (suppress existing issues)
```bash
./gradlew detektBaseline
```

### Auto-fix formatting issues
Enable `autoCorrect = true` in `detekt.yml` under the `formatting` section, then run:
```bash
./gradlew detekt
```

## Configuration

### Main Configuration File
- **Location**: `detekt.yml` (root directory)
- **Max Issues**: Currently set to 1000 (reduce gradually as you fix issues)
- **JVM Target**: 21
- **Build Upon Default**: Yes

### Key Features
- ✅ **Formatting Rules**: Via detekt-formatting plugin
- ✅ **HTML Reports**: `build/reports/detekt/{module}-detekt.html`
- ✅ **XML Reports**: For CI/CD integration
- ✅ **SARIF Reports**: For GitHub code scanning
- ✅ **Baseline Support**: Suppress existing issues

### Report Locations
Reports are generated in each module's build directory:
```
{module}/build/reports/detekt/
├── {module}-detekt.html    # Human-readable report
├── {module}-detekt.xml     # CI/CD integration
├── {module}-detekt.txt     # Console output
└── {module}-detekt.sarif   # GitHub code scanning
```

## Current Status

### Issues Found
As of initial setup:
- **Total Issues**: 338
- **Most Common**:
  - Missing newlines at end of files
  - Lines exceeding 120 characters
  - Unused private properties
  - Code style violations

### Rule Sets Enabled
- **Complexity**: Cyclomatic complexity, long methods, nested blocks
- **Coroutines**: Suspend function checks, coroutine scope usage
- **Empty Blocks**: Catch empty code blocks
- **Exceptions**: Exception handling best practices
- **Formatting**: Code style and formatting (via detekt-formatting)
- **Naming**: Naming conventions for classes, functions, variables
- **Performance**: Performance anti-patterns
- **Potential Bugs**: Common bug patterns
- **Style**: Kotlin idioms and best practices

## Integration

### Applied To
Detekt is automatically applied to all subprojects:
- ✅ app
- ✅ core:data
- ✅ core:domain
- ✅ core:datasource:local
- ✅ core:datasource:remote
- ✅ sacrament
- ✅ feature:presentation

### Build Configuration
Detekt is configured in:
1. **Root `build.gradle.kts`**: Applies to all subprojects
2. **`gradle/libs.versions.toml`**: Version management
3. **`detekt.yml`**: Rule configuration

## Gradual Improvement Strategy

1. **Phase 1** (Current): Set `maxIssues: 1000` to allow builds to pass
2. **Phase 2**: Create baseline with `./gradlew detektBaseline`
3. **Phase 3**: Fix new issues as they arise
4. **Phase 4**: Gradually reduce `maxIssues` threshold
5. **Phase 5**: Enable `autoCorrect` for formatting rules
6. **Phase 6**: Set `maxIssues: 0` for zero-tolerance

## CI/CD Integration

### GitHub Actions Example
```yaml
- name: Run Detekt
  run: ./gradlew detekt

- name: Upload Detekt Reports
  uses: github/codeql-action/upload-sarif@v2
  if: always()
  with:
    sarif_file: build/reports/detekt/detekt.sarif
```

### GitLab CI Example
```yaml
detekt:
  script:
    - ./gradlew detekt
  artifacts:
    reports:
      junit: build/reports/detekt/*.xml
```

## Customization

### Disable Specific Rules
In `detekt.yml`:
```yaml
style:
  MaxLineLength:
    active: false
```

### Configure Rule Thresholds
```yaml
complexity:
  LongMethod:
    active: true
    threshold: 60  # Adjust as needed
```

### Exclude Files/Patterns
```yaml
build:
  exclude:
    - '**/test/**'
    - '**/androidTest/**'
    - '**/*Test.kt'
```

## Resources

- [Detekt Documentation](https://detekt.dev/)
- [Detekt Rules](https://detekt.dev/docs/rules/complexity)
- [Detekt Formatting](https://detekt.dev/docs/rules/formatting)
- [Configuration Options](https://detekt.dev/docs/gettingstarted/configuration)

## Benefits

1. **Code Quality**: Catch issues early
2. **Consistency**: Enforce coding standards
3. **Maintainability**: Easier to read and maintain
4. **Performance**: Identify performance issues
5. **Best Practices**: Follow Kotlin idioms
6. **Team Alignment**: Shared code quality standards

---

**Setup Date**: October 12, 2025  
**Detekt Version**: 1.23.7  
**Status**: ✅ Active and Working
