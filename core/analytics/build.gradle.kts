import com.android.build.api.dsl.LibraryExtension

plugins {
    alias(libs.plugins.practicalchristian.android.library)
}

configure<LibraryExtension> {
    namespace = "com.practicalchristian.app.core.analytics"
}
