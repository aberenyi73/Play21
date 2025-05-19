/**
 * Course work for CIS-135. Final Project 2025 Spring.
 * Author: Antal Berenyi
 * Date: 2025-05-05
 */

package com.aberenyi.play21.mvvm

import androidx.compose.ui.graphics.Color
import com.aberenyi.play21.ui.theme.CreamWhite

data class CardGameState(
    val userScore:Int = 0,
    val machineScore:Int = 0,
    val isGameOver:Boolean = false,
    val isUserTurnUp:Boolean = false,
    val dragColor: Color = CreamWhite,
    val handMessage:String = "",
    val winner: GameRules.Winner = GameRules.Winner.Tie,
    val isSaved:Boolean = false
    ) {
}