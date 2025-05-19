/**
 * Course work for CIS-135. Final Project 2025 Spring.
 * Author: Antal Berenyi
 * Date: 2025-05-05
 */

package com.aberenyi.play21.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.vector.ImageVector
import com.aberenyi.play21.R

/**
 * Navigation routes defined between the various screens: Game, Statistics and Rules.
 */
enum class NavRoutes(val route: String, val title: Int, val icon: ImageVector){
    Game("game",  R.string.game, Icons.Filled.Apps),
    Statistics("statistics", R.string.stats, Icons.Filled.Face),
    Rules("rules", R.string.rules, Icons.Filled.Info)
}

