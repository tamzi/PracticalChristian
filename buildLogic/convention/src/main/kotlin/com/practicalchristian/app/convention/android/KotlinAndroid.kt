package com.practicalchristian.app.convention.android

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.assign
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.provideDelegate
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinBaseExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension


internal fun Project.configureKotlinAndroid(commonExtension: CommonExtension) {
    commonExtension.apply {
        compileSdk = AndroidSdk.COMPILE_SDK

        defaultConfig.minSdk = AndroidSdk.MIN_SDK
        compileOptions.apply {
            // Up to Java 21 APIs are available through desugaring
            // https://developer.android.com/studio/write/java11-minimal-support-table
            sourceCompatibility = JavaVersion.valueOf("VERSION_${AndroidSdk.JAVA_VERSION}")
            targetCompatibility = JavaVersion.valueOf("VERSION_${AndroidSdk.JAVA_VERSION}")
            isCoreLibraryDesugaringEnabled = true
        }
    }

    configureKotlin<KotlinAndroidProjectExtension>()

    dependencies {
        add("coreLibraryDesugaring",
            project.extensions.getByType<VersionCatalogsExtension>().named("libs").findLibrary("android.desugarJdkLibs").get())
    }
}

/**
 *
 * Configure base Kotlin options for JVM (non-Android)
 *
 * This function configures Kotlin options for JVM (non-Android) projects within a Gradle build script.
 * This setup ensures that the project is configured with the necessary Kotlin and Java options,
 * including support for Java 11 APIs.
 *
 * The configureKotlinJvm function is an internal extension function for the Project class in Gradle,
 * designed to configure Kotlin-specific options for JVM (non-Android) projects.
 * This function ensures that the project adheres to specific standards and uses
 * the appropriate versions of Java and Kotlin.
 *
 * Within the function, the `extensions.configure` method is used to configure the `JavaPluginExtension.`
 * This block sets the `sourceCompatibility` and `targetCompatibility` to Java 11,
 * ensuring that the project can use Java 11 APIs:
 *
 * `sourceCompatibility = JavaVersion.VERSION_11
 * targetCompatibility = JavaVersion.VERSION_11`
 *
 * After configuring the Java options, the function calls `configureKotlin`
 * with `KotlinJvmProjectExtension` to apply additional Kotlin-specific configurations.
 * This is done using a generic function that configures Kotlin options based on the project type:
 *
 *
 *  `configureKotlin<KotlinJvmProjectExtension>()`
 *
 *
 */
internal fun Project.configureKotlinJvm() {
    extensions.configure<JavaPluginExtension> {
        // Up to Java 21 APIs are available
        sourceCompatibility = JavaVersion.valueOf("VERSION_${AndroidSdk.JAVA_VERSION}")
        targetCompatibility = JavaVersion.valueOf("VERSION_${AndroidSdk.JAVA_VERSION}")
    }

    configureKotlin<KotlinJvmProjectExtension>()
}

/**
 * Configure base Kotlin options
 *
 * This function configures base Kotlin options for a project within a Gradle build script.
 * This setup ensures that the project adheres to specific standards and uses the appropriate versions of Kotlin.
 *
 * This setup ensures that the project is configured with the necessary Kotlin compiler options,
 * including treating all warnings as errors and enabling experimental coroutines APIs.
 *
 * The configureKotlin function is a private inline extension function for the Project class in Gradle.
 * It is designed to configure Kotlin-specific options for both Android and JVM projects.
 * The function uses a reified type parameter T that extends KotlinBaseExtension,
 * allowing it to be used with different Kotlin project extensions.
 * The function starts by configuring the Kotlin extension of type T using the configure method.
 * It retrieves the warningsAsErrors property from the project,
 * which can be overridden in the gradle.properties file:
 *
 *  `val warningsAsErrors: String? by project`
 *
 *  Next, it uses a when expression to handle different types of Kotlin project extensions.
 *  If the extension is of type KotlinAndroidProjectExtension or KotlinJvmProjectExtension,
 *  it accesses the compilerOptions property.
 *  If the extension type is unsupported, it throws a TO-DO exception:
 *
 *      `when (this) {
 *     is KotlinAndroidProjectExtension -> compilerOptions
 *     is KotlinJvmProjectExtension -> compilerOptions
 *     else -> TODO()
 *      }`
 *
 *      TODO here will always throw a `NotImplementedError` stating that operation is not implemented.
 *  Params: reason - a string explaining why the implementation is missing.
 *
 *  Within the apply block, the function sets the jvmTarget to Java 11 and configures
 *  the allWarningsAsErrors property based on the warningsAsErrors value.
 *  It also adds a free compiler argument to enable experimental coroutines APIs, including Flow:
 *
 *  `jvmTarget = JvmTarget.JVM_11
 *  allWarningsAsErrors = warningsAsErrors.toBoolean()
 *  freeCompilerArgs.add("-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi")`
 *
 *
 */
private inline fun <reified T : KotlinBaseExtension> Project.configureKotlin() =
    configure<T> {
        // Treat all Kotlin warnings as errors (disabled by default)
        // Override by setting warningsAsErrors=true in your ~/.gradle/gradle.properties
        val warningsAsErrors: String? by project
        when (this) {
            is KotlinAndroidProjectExtension -> compilerOptions
            is KotlinJvmProjectExtension -> compilerOptions
            else -> TODO("Unsupported project extension $this ${T::class}")
        }.apply {
            jvmTarget = JvmTarget.JVM_21
            allWarningsAsErrors = warningsAsErrors.toBoolean()
            freeCompilerArgs.add(
                // Enable experimental coroutines APIs, including Flow
                "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            )
        }
    }
