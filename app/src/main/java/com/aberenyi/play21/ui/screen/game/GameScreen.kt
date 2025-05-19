/**
 * Course work for CIS-135. Final Project 2025 Spring.
 * Author: Antal Berenyi
 * Date: 2025-05-05
 */

package com.aberenyi.play21.ui.screen.game

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.aberenyi.play21.mvvm.CardGameViewModel
import com.aberenyi.play21.mvvm.ViewModelUtils
import com.aberenyi.play21.ui.theme.Play21Theme


/**
 * The main layout of game components: Deck to draw from, user Hand, and Score board.
 *
 * @param modifier The modifier to apply to this layout.
 * @param viewModel The view model for the game.
 */
@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    viewModel: CardGameViewModel
) {
    // keep all game state in view model as state, so the UI updates automatically as it changes.
    val gameUiState by viewModel.uiState.collectAsState()

    val configuration = LocalConfiguration.current
    val isPortrait = configuration.orientation == Configuration.ORIENTATION_PORTRAIT

    if (isPortrait) {
        // The main layout of game components: Deck, Hand, Score card.
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        )  {
            // Card Deck
            Row(modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f)
                .padding(18.dp)
            ) {
                CardDealer(viewModel, isPortrait)
            }

            // Score Board
            Row(
                //modifier = Modifier.weight(0.15f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ScoreBoard(gameUiState)
            }

            // User Hand.
            Row(modifier = Modifier
                .fillMaxWidth()
                .weight(0.6f)
                .padding(1.dp),
            ) {
                CardHand(gameUiState, viewModel)
            }



        }
    } else { // landscape
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(
                modifier = Modifier.weight(1f).padding(8.dp),
            ) {
                Row(modifier = Modifier.weight(0.8f)){
                    CardDealer(viewModel, isButtonRight = false)
                }
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        //.weight(0.2f)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ScoreBoard(gameUiState)
                }
                Row(modifier = Modifier.weight(0.8f)){
                    CardHand(gameUiState, viewModel)
                }

            }

        }
    }


}


@Preview(showBackground = true)
@Composable
fun GamePreview() {
    Play21Theme {
        GameScreen(viewModel = ViewModelUtils.cardGameViewModel())
    }
}