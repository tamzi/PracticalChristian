plugins {
    alias(libs.plugins.practicalchristian.android.library)
    alias(libs.plugins.practicalchristian.hilt)
    alias(libs.plugins.practicalchristian.android.library.compose)
}

android {
    namespace = "com.sacrament.ui"
}

dependencies {
    // Core domain module for error handling
    implementation(projects.core.domain)
    
    // Compose BOM
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.runtime)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.foundation.layout)
    debugImplementation(libs.androidx.compose.ui.tooling)
    
    // Material3
    implementation(libs.androidx.compose.material3)
    
    // Foundation
    implementation(libs.androidx.compose.foundation)
    
    // UI
    implementation(libs.androidx.ui)
    
    // Material Icons
    implementation(libs.androidx.compose.material.iconsExtended)

    // Kotlinx DateTime
    implementation(libs.kotlinx.datetime)

    // Coil for image loading
    implementation(libs.coil.kt.compose)

    // palette
    // implementation(libs.androidx.palette)

    // Testing
    testImplementation(libs.junit6)
    testRuntimeOnly(libs.junit.platform.launcher)
}