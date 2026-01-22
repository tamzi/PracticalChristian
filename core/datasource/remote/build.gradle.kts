@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("practicalchristian.android.library")
    id("practicalchristian.hilt")
}

android {
    namespace = "com.practicalchristian.app.core.remotedatasource"
}

dependencies {
    implementation(libs.okhttp)

    // Testing
    testImplementation(libs.junit6)
    testRuntimeOnly(libs.junit.platform.launcher)
}