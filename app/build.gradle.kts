import java.text.SimpleDateFormat

plugins {
    alias(libs.plugins.practicalchristian.android.application)
    alias(libs.plugins.practicalchristian.android.application.compose)
    alias(libs.plugins.practicalchristian.hilt)
    alias(libs.plugins.gms)
    alias(libs.plugins.firebase.crashlytics)
    alias(libs.plugins.firebase.perf)
}

android {

    namespace = "com.practicalchristian.app"

    val date = System.currentTimeMillis()

    defaultConfig {
        applicationId = "com.practicalchristian.app"
        versionCode = 1
        versionName = "v".plus(SimpleDateFormat("yyyy.MM").format(date))
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    packaging {
        resources {
            excludes += "/META-INF/*"
        }
    }

}
dependencies {
    implementation(libs.androidx.work.runtime)

    implementation(projects.core.data)
    implementation(projects.core.domain)
    implementation(projects.core.ui)
    implementation(projects.sacrament)
    implementation(projects.feature.auth)
    implementation(projects.feature.onboarding)
    implementation(projects.feature.landing)
    implementation(projects.feature.setup)
    implementation(projects.feature.notifications)
    implementation(projects.feature.home)
    implementation(projects.feature.notes)
    implementation(projects.feature.books)
    implementation(projects.feature.schedules)
    implementation(projects.feature.settings)
    implementation(projects.feature.tags)
    implementation(projects.feature.profile)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.runtime)
    implementation(libs.androidx.work.ktx)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.hilt.ext.work)
    implementation(libs.timber)
    ksp(libs.hilt.compiler)
    ksp(libs.hilt.ext.compiler)

    // firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.performance)

    // Testing
    testImplementation(libs.junit6)
    testRuntimeOnly(libs.junit.platform.launcher)
    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.androidx.espresso.core)

}
