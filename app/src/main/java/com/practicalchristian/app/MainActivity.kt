package com.practicalchristian.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.practicalchristian.app.core.domain.repository.PreferencesRepository
import com.practicalchristian.app.navigation.PracticalChristianNavigation
import com.sacrament.ui.foundation.Bar
import com.sacrament.ui.foundation.SacramentTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    
    @Inject
    lateinit var preferencesRepository: PreferencesRepository
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        setContent {
            val (navigationBar, setNavigationBar) = remember { mutableStateOf(Bar.SURFACE) }
            
            // Collect dark mode preference from repository
            val isDarkModeEnabled by preferencesRepository.isDarkModeEnabled
                .collectAsStateWithLifecycle(initialValue = true)

            SacramentTheme(
                darkTheme = isDarkModeEnabled,
                navigationBar = navigationBar,
                statusBar = Bar.BACKGROUND
            ) {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    PracticalChristianNavigation(onUpdateNavigationBar = setNavigationBar)
                }
            }
        }
    }
}
