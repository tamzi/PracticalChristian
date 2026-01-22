@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.practicalchristian.android.library)
    alias(libs.plugins.practicalchristian.android.room)
    alias(libs.plugins.practicalchristian.hilt)
}

android {
    namespace = "com.practicalchristian.app.core.localdatasource"
}

dependencies {

    // Core domain module
    implementation(projects.core.domain)

    // datastore
    implementation(libs.androidx.dataStore)
    implementation(libs.androidx.dataStore.core)
    implementation(libs.androidx.datastore.preferences)

    // room
    implementation(libs.androidx.room.testing)
    implementation(libs.room.runtime)
    ksp(libs.room.compiler)

    // work manager
    androidTestImplementation(libs.androidx.work.testing)
    implementation(libs.androidx.work.ktx)
    implementation(libs.hilt.ext.work)
    runtimeOnly(libs.androidx.work.runtime)


    // security
    implementation(libs.androidx.security.crypto)

    // kotlinx serialization
    implementation(libs.kotlinx.serialization.json)

    // kotlinx datetime
    implementation(libs.kotlinx.datetime)

    // timber logging
    implementation(libs.timber)

    // Testing
    testImplementation(libs.junit6)
    testRuntimeOnly(libs.junit.platform.launcher)

}