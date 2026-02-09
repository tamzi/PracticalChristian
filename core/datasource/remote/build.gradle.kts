import com.android.build.api.dsl.LibraryExtension

plugins {
    id("practicalchristian.android.library")
    id("practicalchristian.hilt")
}

configure<LibraryExtension> {
    namespace = "com.practicalchristian.app.core.remotedatasource"
}

dependencies {
    implementation(libs.okhttp)

    // Testing
    testImplementation(libs.junit6)
    testRuntimeOnly(libs.junit.platform.launcher)
}