// Top-level build file where you can add configuration options common to all sub-projects/modules.
// Following the Grapla pattern for better organization and maintainability.

import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.testing.jacoco.tasks.JacocoReport

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.kotlinx.serialization) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.gms) apply false
    alias(libs.plugins.firebase.crashlytics) apply false
    alias(libs.plugins.firebase.perf) apply false
    alias(libs.plugins.detekt)
}

// Configure detekt for the root project
configure<DetektExtension> {
    buildUponDefaultConfig = true
    allRules = false
    config.setFrom(files("$rootDir/detekt.yml"))
    baseline = file("$rootDir/detekt-baseline.xml")
}

// JaCoCo will be applied via convention plugins to individual modules

// Apply detekt to all subprojects
subprojects {
    apply(plugin = "io.gitlab.arturbosch.detekt")
    
    configure<DetektExtension> {
        buildUponDefaultConfig = true
        allRules = false
        config.setFrom(files("$rootDir/detekt.yml"))
        baseline = file("$projectDir/detekt-baseline.xml")
    }
    
    // Configure detekt tasks
    tasks.withType<Detekt>().configureEach {
        jvmTarget = "21"
    }
    
    // Add detekt formatting plugin
    dependencies {
        add("detektPlugins", rootProject.libs.detekt.formatting)
    }
}

// Create root-level JaCoCo report task
tasks.register<JacocoReport>("jacocoRootReport") {
    group = "verification"
    description = "Generate Jacoco coverage report for all modules"
    
    dependsOn(subprojects.map { "${it.name}:jacocoTestReport" })
    
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
    
    val fileFilter = listOf(
        "**/R.class",
        "**/R$*.class",
        "**/BuildConfig.*",
        "**/Manifest*.*",
        "**/*Test*.*",
        "android/**/*.*",
        "**/di/**",
        "**/hilt/**"
    )
    
    val debugTree = fileTree("${project.layout.buildDirectory.get().asFile}") {
        include("**/classes/**/main/**")
        exclude(fileFilter)
    }
    
    val mainSrc = files(subprojects.flatMap { project ->
        listOf("${project.projectDir}/src/main/java")
    })
    
    classDirectories.setFrom(debugTree)
    sourceDirectories.setFrom(mainSrc)
    
    executionData.setFrom(fileTree("${project.layout.buildDirectory.get().asFile}") {
        include("**/jacoco/*.exec")
    })
}

true // Needed to make the Suppress annotation work for the plugins block
