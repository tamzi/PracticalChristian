package com.practicalchristian.app

import androidx.work.Configuration
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PracticalChristianMobileApplication : PracticalChristianApplication(), Configuration.Provider {
    override val workManagerConfiguration: Configuration
        get() = workManagerConfiguration()
}
