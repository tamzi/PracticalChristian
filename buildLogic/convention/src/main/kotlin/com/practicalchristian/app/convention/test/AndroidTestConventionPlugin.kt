package com.practicalchristian.app.convention.test

import com.android.build.gradle.TestExtension
import com.practicalchristian.app.convention.android.AndroidSdk
import com.practicalchristian.app.convention.android.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

/**
 * Plugin for configuring Android test modules
 * 
 * This plugin sets up Android test projects with:
 * - Android test plugin
 * - Kotlin support
 * - JaCoCo code coverage (via convention plugin)
 * - Standardized SDK configuration
 */
class AndroidTestConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.test")
                apply("org.jetbrains.kotlin.android")
                apply("practicalchristian.jacoco") // Use our JaCoCo convention plugin
            }

            extensions.configure<TestExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = AndroidSdk.TARGET_SDK
            }
        }
    }
}
