/**
 * Course work for CIS-135. Final Project 2025 Spring.
 * Author: Antal Berenyi
 * Date: 2025-05-05
 */

package com.aberenyi.play21.ui.screen.game

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.res.stringResource
import com.aberenyi.play21.R
import com.aberenyi.play21.mvvm.CardGameViewModel

@Composable
fun NewGameButton(cardGameViewModel: CardGameViewModel) {
    OutlinedButton(onClick = { cardGameViewModel.resetGame() }) {
        Text(
            text = stringResource(id = R.string.new_game),
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun DrawInstructions() {
    Text(
        text = stringResource(R.string.draw_instructions)
    )
}

@Composable
fun EndTurnButton(cardGameViewModel: CardGameViewModel) {
    Button(
        modifier = Modifier.clipToBounds(),
        colors = ButtonColors(
            contentColor = MaterialTheme.colorScheme.primary,
            containerColor = MaterialTheme.colorScheme.error,
            disabledContentColor = MaterialTheme.colorScheme.surfaceBright,
            disabledContainerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
        ),
        onClick = { cardGameViewModel.endTurn() }
    ) { Text(stringResource(R.string.end_turn)) }
}