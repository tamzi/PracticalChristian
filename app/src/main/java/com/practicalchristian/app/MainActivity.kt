package com.practicalchristian.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.practicalchristian.app.navigation.PracticalChristianNavigation
import com.sacrament.ui.foundation.Bar
import com.sacrament.ui.foundation.SacramentTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val (navigationBar, setNavigationBar) = remember { mutableStateOf(Bar.SURFACE) }

            SacramentTheme(
                navigationBar = navigationBar,
                statusBar = Bar.BACKGROUND
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(SacramentTheme.colors.surfaces.background),
                ) {
                    PracticalChristianNavigation(onUpdateNavigationBar = setNavigationBar)
                }
            }
        }
    }
}
