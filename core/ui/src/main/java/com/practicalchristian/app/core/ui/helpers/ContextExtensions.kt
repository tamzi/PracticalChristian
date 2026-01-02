package com.practicalchristian.app.core.ui.helpers

import android.content.Context
import android.content.pm.PackageInfo

private val Context.packageInfo: PackageInfo
    get() = packageManager.getPackageInfo(packageName, 0)

val Context.versionName: String
    get() = packageInfo.versionName.orEmpty()

val Context.versionCode: String
    get() = packageInfo.versionName.orEmpty()
