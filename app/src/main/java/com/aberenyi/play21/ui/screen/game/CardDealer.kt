/**
 * Course work for CIS-135. Final Project 2025 Spring.
 * Author: Antal Berenyi
 * Date: 2025-05-05
 */

package com.aberenyi.play21.ui.screen.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aberenyi.play21.R
import com.aberenyi.play21.mvvm.CardGameViewModel

/**
 * Show the card deck and the button to start a new game and end the turn.
 * @param cardGameViewModel The view model for the game.
 * @param isButtonRight If true, the buttons show on the right side of the deck, which is the default.
 *
 */
@Composable
fun CardDealer(
    cardGameViewModel: CardGameViewModel,
    isButtonRight: Boolean = true
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,

        ) {

            if(isButtonRight)
                DynamicImage(
                    resourceId = R.drawable.blue,
                    contentDescription = "Card Back"
                )

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            )
            {
                NewGameButton(cardGameViewModel)
                DrawInstructions()
                EndTurnButton(cardGameViewModel)
            }

            if(isButtonRight.not())
                DynamicImage(
                    resourceId = R.drawable.blue,
                    contentDescription = "Card Back"
                )




    }
}