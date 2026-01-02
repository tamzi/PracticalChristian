# JaCoCo Convention Plugins

This document explains how to use the JaCoCo convention plugins for code coverage in the PracticalChristian app.

## Available Plugins

### 1. `practicalchristian.jacoco`
A standalone JaCoCo convention plugin that can be applied to any module that needs code coverage.

**Features:**
- JaCoCo plugin application
- Standard configuration with version 0.8.7
- Coverage verification with 70% minimum overall coverage
- 60% minimum line coverage per class
- Comprehensive file filtering (excludes generated code, tests, etc.)
- HTML and XML report generation

### 2. `practicalchristian.android.library.coverage`
An Android library convention plugin that includes JaCoCo coverage.

**Features:**
- All features of `practicalchristian.android.library`
- Plus JaCoCo code coverage configuration
- Use this for library modules where you want to track test coverage

## Usage

### For Test Modules
Test modules automatically get JaCoCo coverage via the `practicalchristian.android.test` plugin.

See `buildLogic/convention/src/main/kotlin/` for plugin implementations.

### For Library Modules with Coverage
Use the coverage-enabled library plugin: `practicalchristian.android.library.coverage`

### For Any Module (Standalone)
Apply the JaCoCo plugin directly: `practicalchristian.jacoco`

**Example:** See any module's `build.gradle.kts` for plugin application.

### For Root Project
The root project automatically applies JaCoCo for aggregated coverage reports.

## Coverage Rules

### Minimum Coverage Requirements
- **Overall Coverage**: 70%
- **Line Coverage per Class**: 60%

### Excluded Files
The following files are automatically excluded from coverage:
- Generated files (`R.class`, `BuildConfig.*`, etc.)
- Test files (`*Test*.*`)
- Android framework files (`android/**/*.*`)
- Dependency injection files (`**/di/**`, `**/hilt/**`, `**/dagger/**`)
- Generated Dagger/Hilt files (`*_Factory.*`, `*_MembersInjector.*`, etc.)

## Running Coverage Reports

### Individual Module Coverage
```bash
./gradlew :module-name:jacocoTestReport
```

### Coverage Verification
```bash
./gradlew :module-name:jacocoTestCoverageVerification
```

### Root Project Coverage (All Modules)
```bash
./gradlew jacocoRootReport
```

## Report Locations

### HTML Reports
- Individual modules: `module/build/reports/jacoco/test/html/index.html`
- Root project: `build/reports/jacoco/jacocoRootReport/html/index.html`

### XML Reports
- Individual modules: `module/build/reports/jacoco/test/jacocoTestReport.xml`
- Root project: `build/reports/jacoco/jacocoRootReport/jacocoRootReport.xml`

## Customization

### Adjusting Coverage Thresholds
To modify coverage requirements, override verification rules in your module's `build.gradle.kts`.

See `buildLogic/convention/src/main/kotlin/JacocoConventionPlugin.kt` for default configuration.

### Adding Custom Exclusions
To exclude additional files, modify the `fileFilter` list in your Jacoco configuration.

See convention plugin implementation for default exclusion patterns.

## Best Practices

1. **Use Coverage-Enabled Plugins**: Prefer `practicalchristian.android.library.coverage` over manually applying JaCoCo
2. **Set Realistic Thresholds**: 70% overall coverage is a good starting point
3. **Focus on Business Logic**: Don't worry about covering simple data classes or generated code
4. **Regular Monitoring**: Run coverage reports regularly to catch regressions
5. **CI Integration**: Consider adding coverage verification to your CI pipeline

## Troubleshooting

### Common Issues

1. **No Coverage Data**: Ensure tests are running and generating `.exec` files
2. **High Coverage but Low Quality**: Focus on meaningful tests, not just coverage numbers
3. **Build Failures**: Check that all required dependencies are available

### Debug Commands
```bash
# Check if JaCoCo is applied
./gradlew :module-name:tasks --group="verification"

# Run tests with verbose output
./gradlew :module-name:testDebugUnitTest --info

# Check coverage data generation
ls -la module/build/jacoco/
```
