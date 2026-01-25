package com.practicalchristian.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.practicalchristian.app.core.domain.repository.PreferencesRepository
import com.practicalchristian.app.navigation.PracticalChristianNavigation
import com.sacrament.ui.foundation.Bar
import com.sacrament.ui.foundation.SacramentTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
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
                .stateIn(
                    scope = lifecycleScope,
                    started = SharingStarted.WhileSubscribed(5000),
                    initialValue = true
                )
                .collectAsState()

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
