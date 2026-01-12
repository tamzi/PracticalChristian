package com.sacrament.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sacrament.ui.foundation.Bar
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.patterns.SacramentScreenScaffold
import com.sacrament.demo.catalog.CatalogHomeScreen
import com.sacrament.demo.catalog.action.ActionCatalogScreen
import com.sacrament.demo.catalog.content.ContentCatalogScreen
import com.sacrament.demo.catalog.feedback.FeedbackCatalogScreen
import com.sacrament.demo.catalog.input.InputCatalogScreen
import com.sacrament.demo.catalog.navigation.NavigationCatalogScreen
import com.sacrament.demo.catalog.patterns.PatternsCatalogScreen
import com.sacrament.demo.catalog.surface.SurfaceCatalogScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SacramentTheme(
                navigationBar = Bar.SURFACE,
                statusBar = Bar.BACKGROUND
            ) {
                CatalogApp()
            }
        }
    }
}

@Composable
fun CatalogApp() {
    val navController = rememberNavController()
    
    SacramentScreenScaffold(
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("home") {
                CatalogHomeScreen(
                    onNavigateToAction = { navController.navigate("action") },
                    onNavigateToInput = { navController.navigate("input") },
                    onNavigateToNavigation = { navController.navigate("navigation") },
                    onNavigateToSurface = { navController.navigate("surface") },
                    onNavigateToContent = { navController.navigate("content") },
                    onNavigateToFeedback = { navController.navigate("feedback") },
                    onNavigateToPatterns = { navController.navigate("patterns") },
                )
            }
            composable("action") {
                ActionCatalogScreen(onNavigateBack = { navController.popBackStack() })
            }
            composable("input") {
                InputCatalogScreen(onNavigateBack = { navController.popBackStack() })
            }
            composable("navigation") {
                NavigationCatalogScreen(onNavigateBack = { navController.popBackStack() })
            }
            composable("surface") {
                SurfaceCatalogScreen(onNavigateBack = { navController.popBackStack() })
            }
            composable("content") {
                ContentCatalogScreen(onNavigateBack = { navController.popBackStack() })
            }
            composable("feedback") {
                FeedbackCatalogScreen(onNavigateBack = { navController.popBackStack() })
            }
            composable("patterns") {
                PatternsCatalogScreen(onNavigateBack = { navController.popBackStack() })
            }
        }
    }
}

