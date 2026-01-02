@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.practicalchristian.android.library)
    alias(libs.plugins.practicalchristian.hilt)
}

android {
    namespace = "com.practicalchristian.app.core.domain"
}

dependencies {
    // kotlinx datetime
    implementation(libs.kotlinx.datetime)
}