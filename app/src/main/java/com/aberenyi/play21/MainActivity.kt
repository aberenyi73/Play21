/**
 * Course work for CIS-135. Final Project 2025 Spring.
 * Author: Antal Berenyi
 * Date: 2025-05-05
 */

package com.aberenyi.play21

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.aberenyi.play21.mvvm.CardGameViewModel
import com.aberenyi.play21.mvvm.ViewModelUtils
import com.aberenyi.play21.ui.navigation.NavRoutes
import com.aberenyi.play21.ui.navigation.NavigationBottomBar
import com.aberenyi.play21.ui.navigation.NavigationGraph
import com.aberenyi.play21.ui.theme.Play21Theme

/**
 * The main activity of the application.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Play21Theme(dynamicColor = false) {
                MainScreen(ViewModelUtils.cardGameViewModel())
            }
        }
    }
}

/**
 * The main screen of the application. It creates a scaffold with a top bar, a bottom bar, and a
 * content as defined by the navigation host, GameNavigation.
 */
@Composable
fun MainScreen(viewModel: CardGameViewModel) {

    // Navigation Controller
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route?: NavRoutes.Game.route

    val layoutModifier = Modifier
        .background(MaterialTheme.colorScheme.primaryContainer) // color behind status bar is according to dark/light mode
        .statusBarsPadding()
        .navigationBarsPadding()

    Scaffold(
        modifier = layoutModifier,
        topBar = { GameTopBar() },
        bottomBar = { NavigationBottomBar(navController,currentRoute) }
    ) {
        // content
        innerPadding ->
        NavigationGraph(navController, innerPadding, viewModel)
    }
}


/**
 * Display the game name at the top of the screen.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun GameTopBar() {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary),
        title = {
            Text(
                stringResource(R.string.game_name)
            )
        },
        actions = {
            IconButton(onClick = { /* do something */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.app_launch2_play21),
                    contentDescription = stringResource(id = R.string.app_name),
                    modifier = Modifier.requiredSize(48.dp),
                    tint = Color.Unspecified
                )
            }
        }
    )


}