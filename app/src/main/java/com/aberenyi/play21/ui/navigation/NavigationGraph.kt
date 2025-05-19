/**
 * Course work for CIS-135. Final Project 2025 Spring.
 * Author: Antal Berenyi
 * Date: 2025-05-05
 */

package com.aberenyi.play21.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aberenyi.play21.mvvm.CardGameViewModel
import com.aberenyi.play21.ui.screen.game.GameScreen
import com.aberenyi.play21.ui.screen.rules.RulesScreen
import com.aberenyi.play21.ui.screen.stats.StatScreen

/**
 * Navigation graph for the app. There are three screens: Game, Statistics and Rules.
 *
 * @param navController The navigation controller
 */
@Composable
fun NavigationGraph(
    navController: NavHostController,
    innerPadding: PaddingValues,
    viewModel: CardGameViewModel
) {
    NavHost(
        navController = navController,
        startDestination = NavRoutes.Game.route,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable(NavRoutes.Game.route) {
            GameScreen(viewModel = viewModel)
        }
        composable(NavRoutes.Rules.route) {
            RulesScreen()
        }
        composable(NavRoutes.Statistics.route) {
            StatScreen(viewModel = viewModel)
        }
    }
}