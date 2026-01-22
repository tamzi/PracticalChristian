@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.practicalchristian.android.library)
    alias(libs.plugins.practicalchristian.hilt)
}

android {
    namespace = "com.practicalchristian.app.core.data"
}

dependencies {

    implementation(projects.core.domain)
    implementation(projects.core.datasource.local)
    implementation(projects.core.datasource.remote)
    implementation(libs.kotlinx.datetime)
    implementation(libs.timber)

    // Testing
    testImplementation(libs.junit6)
    testRuntimeOnly(libs.junit.platform.launcher)

}
