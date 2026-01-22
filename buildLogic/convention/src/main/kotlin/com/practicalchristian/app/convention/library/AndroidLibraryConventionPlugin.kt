package com.practicalchristian.app.convention.library

import com.android.build.api.dsl.LibraryExtension
import com.android.build.api.variant.LibraryAndroidComponentsExtension
import com.practicalchristian.app.convention.android.configureKotlinAndroid
import com.practicalchristian.app.convention.android.libs
import com.practicalchristian.app.convention.test.disableUnnecessaryAndroidTests
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.withType

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                defaultConfig {
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }
                testOptions {
                    animationsDisabled = true
                }
                // The resource prefix is derived from the module name,
                // so resources inside ":core:module1" must be prefixed with "core_module1_"
                resourcePrefix = path
                    .split("""\W""".toRegex())
                    .drop(1)
                    .distinct()
                    .joinToString(separator = "_")
                    .lowercase() + "_"
            }
            extensions.configure<LibraryAndroidComponentsExtension> {
                disableUnnecessaryAndroidTests(target)
            }
            
            // Configure test tasks to use JUnit Platform for JUnit 6 (Jupiter)
            tasks.withType<Test> {
                useJUnitPlatform()
            }
            
            dependencies {
                add("androidTestImplementation", kotlin("test"))
                add("testImplementation", kotlin("test"))

                // JUnit 6 (Jupiter) dependencies for unit testing
                add("testImplementation", libs.findLibrary("junit6").get())
                add("testRuntimeOnly", libs.findLibrary("junit.platform.launcher").get())

                add("implementation", libs.findLibrary("androidx.tracing.ktx").get())
                //add("implementation", project.findProperty("libs.androidx.tracing.ktx") as Provider<*>)
            }
        }
    }
}
