package com.practicalchristian.app.convention.application

import com.android.build.api.dsl.ApplicationExtension
import com.practicalchristian.app.convention.android.AndroidSdk
import com.practicalchristian.app.convention.android.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = AndroidSdk.TARGET_SDK
                testOptions.animationsDisabled = true
            }
            
            // Configure test tasks to use JUnit Platform for JUnit 6 (Jupiter)
            tasks.withType<Test> {
                useJUnitPlatform()
            }
        }
    }
}
