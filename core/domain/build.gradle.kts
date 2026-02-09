import com.android.build.api.dsl.LibraryExtension

plugins {
    alias(libs.plugins.practicalchristian.android.library)
    alias(libs.plugins.practicalchristian.hilt)
}

configure<LibraryExtension> {
    namespace = "com.practicalchristian.app.core.domain"
}

dependencies {
    // kotlinx datetime
    implementation(libs.kotlinx.datetime)

    // Testing
    testImplementation(libs.junit6)
    testRuntimeOnly(libs.junit.platform.launcher)
}