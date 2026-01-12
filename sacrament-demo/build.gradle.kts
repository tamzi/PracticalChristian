plugins {
    alias(libs.plugins.practicalchristian.android.application)
    alias(libs.plugins.practicalchristian.android.application.compose)
}

android {
    namespace = "com.sacrament.demo"

    defaultConfig {
        applicationId = "com.sacrament.demo"
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
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
    implementation(projects.sacrament)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.runtime)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.material.icons.extended)
}

