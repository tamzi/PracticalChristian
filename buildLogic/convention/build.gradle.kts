import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

group = "com.practicalchristian.app.convention"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_21
    }
    jvmToolchain(21)
}

dependencies {
    implementation(libs.kotlin.gradlePlugin)
    implementation(libs.android.gradlePlugin)
    implementation(libs.compose.gradlePlugin)
    implementation(libs.detekt.gradlePlugin)
    implementation(libs.ksp.gradlePlugin)
    implementation(libs.room.gradlePlugin)
    implementation(libs.firebase.crashlytics.gradlePlugin)
    implementation(libs.firebase.performance.gradlePlugin)
    implementation(libs.kover.gradlePlugin)
    implementation(libs.truth)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("hilt") {
            id = "practicalchristian.hilt"
            implementationClass = "com.practicalchristian.app.convention.hilt.HiltConventionPlugin"
        }
        register("androidApplicationCompose") {
            id = "practicalchristian.android.application.compose"
            implementationClass =
                "com.practicalchristian.app.convention.application.AndroidApplicationComposeConventionPlugin"
        }
        register("androidApplication") {
            id = "practicalchristian.android.application"
            implementationClass = "com.practicalchristian.app.convention.application.AndroidApplicationConventionPlugin"
        }
        register("androidApplicationFirebase") {
            id = "practicalchristian.android.application.firebase"
            implementationClass =
                "com.practicalchristian.app.convention.application.AndroidApplicationFirebaseConventionPlugin"
        }
        register("androidFeature") {
            id = "practicalchristian.android.feature"
            implementationClass = "com.practicalchristian.app.convention.feature.AndroidFeatureConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "practicalchristian.android.library.compose"
            implementationClass = "com.practicalchristian.app.convention.library.AndroidLibraryComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = "practicalchristian.android.library"
            implementationClass = "com.practicalchristian.app.convention.library.AndroidLibraryConventionPlugin"
        }
        register("androidLint") {
            id = "practicalchristian.android.lint"
            implementationClass = "com.practicalchristian.app.convention.library.AndroidLintConventionPlugin"
        }
        register("androidRoom") {
            id = "practicalchristian.android.room"
            implementationClass = "com.practicalchristian.app.convention.library.AndroidRoomConventionPlugin"
        }
        register("androidTest") {
            id = "practicalchristian.android.test"
            implementationClass = "com.practicalchristian.app.convention.test.AndroidTestConventionPlugin"
        }
        register("jvmLibrary") {
            id = "practicalchristian.jvm.library"
            implementationClass = "com.practicalchristian.app.convention.jvm.JvmLibraryConventionPlugin"
        }
        register("jacoco") {
            id = "practicalchristian.jacoco"
            implementationClass = "com.practicalchristian.app.convention.jacoco.JacocoConventionPlugin"
        }
        register("androidLibraryCoverage") {
            id = "practicalchristian.android.library.coverage"
            implementationClass = "com.practicalchristian.app.convention.library.AndroidLibraryWithCoverageConventionPlugin"
        }
    }
}
