plugins {
    alias(libs.plugins.practicalchristian.android.feature)
}

android {
    namespace = "com.practicalchristian.app.feature.notes"
}

dependencies {
    implementation(libs.rich.editor)
}
