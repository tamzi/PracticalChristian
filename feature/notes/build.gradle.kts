import com.android.build.api.dsl.LibraryExtension

plugins {
    alias(libs.plugins.practicalchristian.android.feature)
}

configure<LibraryExtension> {
    namespace = "com.practicalchristian.app.feature.notes"
}

dependencies {
    implementation(libs.rich.editor)
}
