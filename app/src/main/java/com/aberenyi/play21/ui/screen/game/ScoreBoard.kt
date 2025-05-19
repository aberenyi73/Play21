/**
 * Course work for CIS-135. Final Project 2025 Spring.
 * Author: Antal Berenyi
 * Date: 2025-05-05
 */

package com.aberenyi.play21.ui.screen.game

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.aberenyi.play21.R
import com.aberenyi.play21.mvvm.CardGameState
import com.aberenyi.play21.ui.theme.WinInitialValue
import com.aberenyi.play21.ui.theme.WinTargetValue


/**
 * Animated score display. Flash the score when the game is over.
 */
@Composable
fun ScoreBoard(gameUiState: CardGameState) {
    AnimatedVisibility(gameUiState.isGameOver) {
            val transition = rememberInfiniteTransition(label = "color transition")
            val color by transition.animateColor(
                initialValue = WinInitialValue,
                targetValue = WinTargetValue,
                animationSpec = infiniteRepeatable(
                    animation = tween(1000),
                    repeatMode = RepeatMode.Reverse
                ),
                label = "color cycling"
            )

            Column(modifier = Modifier.animateEnterExit()) {
                Text(
                    stringResource(R.string.winner, gameUiState.winner),
                    style = MaterialTheme.typography.headlineSmall,
                    color = color,
                    modifier = Modifier
                )
                Text(
                    stringResource(R.string.scores, gameUiState.userScore, gameUiState.machineScore),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
