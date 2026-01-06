package com.practicalchristian.app.convention.jvm

import com.practicalchristian.app.convention.android.configureKotlinJvm
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.withType

class JvmLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.jvm")
            }
            configureKotlinJvm()
            
            // Configure test tasks to use JUnit Platform for JUnit 6 (Jupiter)
            tasks.withType<Test> {
                useJUnitPlatform()
            }
        }
    }
}
