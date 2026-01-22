package com.practicalchristian.app.convention.hilt

import com.android.build.gradle.api.AndroidBasePlugin
import com.practicalchristian.app.convention.android.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

private fun Project.configureHiltAndroid() {
    pluginManager.apply("dagger.hilt.android.plugin")
    dependencies {
        add("implementation", libs.findLibrary("hilt.android").get())
    }
}

class HiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.google.devtools.ksp")
            dependencies {
                add("ksp", libs.findLibrary("hilt.compiler").get())
                add("implementation", libs.findLibrary("hilt.core").get())
            }

            /** Add support for Android modules, based on [AndroidBasePlugin] */
            // Use plugins.withId to properly wait for the Android plugin to be applied
            plugins.withId("com.android.application") {
                configureHiltAndroid()
            }
            
            plugins.withId("com.android.library") {
                configureHiltAndroid()
            }
        }
    }
}
