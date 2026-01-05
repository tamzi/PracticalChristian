enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    includeBuild("buildLogic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id == "com.google.firebase.firebase-perf") {
                useModule("com.google.firebase:perf-plugin:${requested.version}")
            }
        }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "PracticalChristian"

// Auto-install git hooks on Gradle sync
// This ensures all developers have hooks installed automatically
gradle.settingsEvaluated {
    val hooksScript = rootProject.projectDir.resolve("scripts/auto-install-hooks.sh")
    if (hooksScript.exists()) {
        try {
            val process = ProcessBuilder("bash", hooksScript.absolutePath)
                .directory(rootProject.projectDir)
                .redirectErrorStream(true)
                .start()

            val exitCode = process.waitFor()
            if (exitCode != 0) {
                val output = process.inputStream.bufferedReader().readText()
                logger.warn("Git hooks auto-installation had issues:\n$output")
            }
        } catch (e: Exception) {
            logger.warn("Could not auto-install git hooks: ${e.message}")
        }
    }
}

// App & Design System
include(":app")
include(":sacrament")
include(":sacrament-demo")

// Tools / infra
include(":sync:work")

// Core – Foundations
include(":core:common")
include(":core:model")
include(":core:datastore")
include(":core:database")
include(":core:network")
include(":core:analytics")
include(":core:notifications")
include(":core:media")
include(":core:performance")
include(":core:i18n")
include(":core:testing")
include(":core:ui")
include(":core:datasource:local")
include(":core:datasource:remote")
include(":core:data")
include(":core:domain")

// Content – App content (data-only)
include(":content:books")
include(":content:meditations")
include(":content:plans")
include(":content:prayers")
include(":content:themes")
include(":content:audio")

// Legacy modules (to migrate)

// Feature modules (new)
include(":feature:auth")
include(":feature:landing")
include(":feature:setup")
include(":feature:onboarding")
include(":feature:home")
include(":feature:meditation")
include(":feature:books")
include(":feature:plans")
include(":feature:schedules")
include(":feature:tags")
include(":feature:notes")
include(":feature:journal")
include(":feature:prayer")
include(":feature:audio")
include(":feature:streaks")
include(":feature:notifications")
include(":feature:profile")
include(":feature:settings")
include(":feature:search")
include(":feature:bookmarks")
