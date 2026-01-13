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
import com.sacrament.demo.action.button.screens.ButtonCatalogScreen
import com.sacrament.demo.action.button.screens.ButtonIconsCatalogScreen
import com.sacrament.demo.action.button.screens.ButtonSizesCatalogScreen
import com.sacrament.demo.action.button.screens.ButtonTonesCatalogScreen
import com.sacrament.demo.action.button.screens.ButtonVariantsCatalogScreen
import com.sacrament.demo.action.fab.screens.FloatingActionButtonsCatalogScreen
import com.sacrament.demo.action.iconbutton.screens.IconButtonsCatalogScreen
import com.sacrament.demo.content.screens.AvatarsCatalogScreen
import com.sacrament.demo.content.screens.BadgesCatalogScreen
import com.sacrament.demo.content.screens.ChipsCatalogScreen
import com.sacrament.demo.content.screens.ContentCatalogOverviewScreen
import com.sacrament.demo.content.screens.TagsCatalogScreen
import com.sacrament.demo.feedback.screens.FeedbackCatalogOverviewScreen
import com.sacrament.demo.feedback.screens.InlineMessagesCatalogScreen
import com.sacrament.demo.feedback.screens.ProgressIndicatorsCatalogScreen
import com.sacrament.demo.input.screens.CheckboxesCatalogScreen
import com.sacrament.demo.input.screens.InputCatalogOverviewScreen
import com.sacrament.demo.input.screens.RadiosCatalogScreen
import com.sacrament.demo.input.screens.SwitchesCatalogScreen
import com.sacrament.demo.input.screens.TextFieldsCatalogScreen
import com.sacrament.demo.navigation.screens.BottomBarCatalogScreen
import com.sacrament.demo.navigation.screens.NavigationCatalogOverviewScreen
import com.sacrament.demo.navigation.screens.NavigationRailCatalogScreen
import com.sacrament.demo.navigation.screens.TabRowCatalogScreen
import com.sacrament.demo.navigation.screens.TopAppBarCatalogScreen
import com.sacrament.demo.patterns.screens.EmptyStateCatalogScreen
import com.sacrament.demo.patterns.screens.ErrorStateCatalogScreen
import com.sacrament.demo.patterns.screens.LoadingStateCatalogScreen
import com.sacrament.demo.patterns.screens.PatternsCatalogOverviewScreen
import com.sacrament.demo.patterns.screens.ScreenScaffoldCatalogScreen
import com.sacrament.demo.surface.screens.CardsCatalogScreen
import com.sacrament.demo.surface.screens.DialogsCatalogScreen
import com.sacrament.demo.surface.screens.SheetsCatalogScreen
import com.sacrament.demo.surface.screens.SurfaceCatalogOverviewScreen
import com.sacrament.ui.foundation.Bar
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.patterns.SacramentScreenScaffold

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
                _root_ide_package_.com.sacrament.demo.action.ActionCatalogScreen(
                    onNavigateBack = { navController.popBackStack() },
                    onNavigateToButtons = { navController.navigate("action/buttons") },
                    onNavigateToIconButtons = { navController.navigate("action/iconbuttons") },
                    onNavigateToFABs = { navController.navigate("action/fabs") }
                )
            }
            composable("action/buttons") {
                ButtonCatalogScreen(
                    onNavigateBack = { navController.popBackStack() },
                    onNavigateToVariants = { navController.navigate("action/buttons/variants") },
                    onNavigateToSizes = { navController.navigate("action/buttons/sizes") },
                    onNavigateToTones = { navController.navigate("action/buttons/tones") },
                    onNavigateToIcons = { navController.navigate("action/buttons/icons") }
                )
            }
            composable("action/buttons/variants") {
                ButtonVariantsCatalogScreen(
                    onNavigateBack = { navController.popBackStack() })
            }
            composable("action/buttons/sizes") {
                ButtonSizesCatalogScreen(
                    onNavigateBack = { navController.popBackStack() })
            }
            composable("action/buttons/tones") {
                ButtonTonesCatalogScreen(
                    onNavigateBack = { navController.popBackStack() })
            }
            composable("action/buttons/icons") {
                ButtonIconsCatalogScreen(
                    onNavigateBack = { navController.popBackStack() })
            }
            composable("action/iconbuttons") {
                IconButtonsCatalogScreen(
                    onNavigateBack = { navController.popBackStack() })
            }
            composable("action/fabs") {
                FloatingActionButtonsCatalogScreen(
                    onNavigateBack = { navController.popBackStack() })
            }
            composable("input") {
                InputCatalogOverviewScreen(
                    onNavigateBack = { navController.popBackStack() },
                    onNavigateToTextFields = { navController.navigate("input/textfields") },
                    onNavigateToCheckboxes = { navController.navigate("input/checkboxes") },
                    onNavigateToRadios = { navController.navigate("input/radios") },
                    onNavigateToSwitches = { navController.navigate("input/switches") }
                )
            }
            composable("input/textfields") {
                TextFieldsCatalogScreen(onNavigateBack = { navController.popBackStack() })
            }
            composable("input/checkboxes") {
                CheckboxesCatalogScreen(onNavigateBack = { navController.popBackStack() })
            }
            composable("input/radios") {
                RadiosCatalogScreen(onNavigateBack = { navController.popBackStack() })
            }
            composable("input/switches") {
                SwitchesCatalogScreen(onNavigateBack = { navController.popBackStack() })
            }
            composable("navigation") {
                NavigationCatalogOverviewScreen(
                    onNavigateBack = { navController.popBackStack() },
                    onNavigateToTopAppBar = { navController.navigate("navigation/topappbar") },
                    onNavigateToBottomBar = { navController.navigate("navigation/bottombar") },
                    onNavigateToTabRow = { navController.navigate("navigation/tabrow") },
                    onNavigateToNavigationRail = { navController.navigate("navigation/rail") }
                )
            }
            composable("navigation/topappbar") {
                TopAppBarCatalogScreen(
                    onNavigateBack = { navController.popBackStack() })
            }
            composable("navigation/bottombar") {
                BottomBarCatalogScreen(
                    onNavigateBack = { navController.popBackStack() })
            }
            composable("navigation/tabrow") {
                TabRowCatalogScreen(
                    onNavigateBack = { navController.popBackStack() })
            }
            composable("navigation/rail") {
                NavigationRailCatalogScreen(
                    onNavigateBack = { navController.popBackStack() })
            }
            composable("surface") {
                SurfaceCatalogOverviewScreen(
                    onNavigateBack = { navController.popBackStack() },
                    onNavigateToCards = { navController.navigate("surface/cards") },
                    onNavigateToDialogs = { navController.navigate("surface/dialogs") },
                    onNavigateToSheets = { navController.navigate("surface/sheets") }
                )
            }
            composable("surface/cards") {
                CardsCatalogScreen(
                    onNavigateBack = { navController.popBackStack() })
            }
            composable("surface/dialogs") {
                DialogsCatalogScreen(
                    onNavigateBack = { navController.popBackStack() })
            }
            composable("surface/sheets") {
                SheetsCatalogScreen(
                    onNavigateBack = { navController.popBackStack() })
            }
            composable("content") {
                ContentCatalogOverviewScreen(
                    onNavigateBack = { navController.popBackStack() },
                    onNavigateToAvatars = { navController.navigate("content/avatars") },
                    onNavigateToBadges = { navController.navigate("content/badges") },
                    onNavigateToChips = { navController.navigate("content/chips") },
                    onNavigateToTags = { navController.navigate("content/tags") }
                )
            }
            composable("content/avatars") {
                AvatarsCatalogScreen(onNavigateBack = { navController.popBackStack() })
            }
            composable("content/badges") {
                BadgesCatalogScreen(onNavigateBack = { navController.popBackStack() })
            }
            composable("content/chips") {
                ChipsCatalogScreen(onNavigateBack = { navController.popBackStack() })
            }
            composable("content/tags") {
                TagsCatalogScreen(onNavigateBack = { navController.popBackStack() })
            }
            composable("feedback") {
                FeedbackCatalogOverviewScreen(
                    onNavigateBack = { navController.popBackStack() },
                    onNavigateToProgressIndicators = { navController.navigate("feedback/progress") },
                    onNavigateToInlineMessages = { navController.navigate("feedback/messages") }
                )
            }
            composable("feedback/progress") {
                ProgressIndicatorsCatalogScreen(
                    onNavigateBack = { navController.popBackStack() })
            }
            composable("feedback/messages") {
                InlineMessagesCatalogScreen(
                    onNavigateBack = { navController.popBackStack() })
            }
            composable("patterns") {
                PatternsCatalogOverviewScreen(
                    onNavigateBack = { navController.popBackStack() },
                    onNavigateToScaffold = { navController.navigate("patterns/scaffold") },
                    onNavigateToEmptyState = { navController.navigate("patterns/empty") },
                    onNavigateToErrorState = { navController.navigate("patterns/error") },
                    onNavigateToLoadingState = { navController.navigate("patterns/loading") }
                )
            }
            composable("patterns/scaffold") {
                ScreenScaffoldCatalogScreen(
                    onNavigateBack = { navController.popBackStack() })
            }
            composable("patterns/empty") {
                EmptyStateCatalogScreen(
                    onNavigateBack = { navController.popBackStack() })
            }
            composable("patterns/error") {
                ErrorStateCatalogScreen(
                    onNavigateBack = { navController.popBackStack() })
            }
            composable("patterns/loading") {
                LoadingStateCatalogScreen(
                    onNavigateBack = { navController.popBackStack() })
            }
        }
    }
}

