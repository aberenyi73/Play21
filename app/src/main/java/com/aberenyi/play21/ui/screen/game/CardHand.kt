/**
 * Course work for CIS-135. Final Project 2025 Spring.
 * Author: Antal Berenyi
 * Date: 2025-05-05
 */

package com.aberenyi.play21.ui.screen.game

import android.content.ClipDescription
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.draganddrop.dragAndDropTarget
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draganddrop.DragAndDropEvent
import androidx.compose.ui.draganddrop.DragAndDropTarget
import androidx.compose.ui.draganddrop.mimeTypes
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.aberenyi.play21.R
import com.aberenyi.play21.mvvm.CardGameState
import com.aberenyi.play21.mvvm.CardGameViewModel
import com.aberenyi.play21.ui.theme.CardDragEntered
import com.aberenyi.play21.ui.theme.DarkFeltGreen

/**
 * This is the most complex function. It is responsible for displaying the hand of cards drawn by
 * the user, and for handling the drag-and-drop events. It also displays the current value of
 * the cards in the hand and a hint for the user on what to do next.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CardHand(
    gameUiState: CardGameState,
    cardGameViewModel: CardGameViewModel
) {

    // Drag-and-drop color change of Hand module
    var tintColor by remember { mutableStateOf(DarkFeltGreen) }

    // Drag-and-drop target. Add card to Hand when dropped and change color while dragging.
    val dndTarget = remember {
        object : DragAndDropTarget {
            override fun onDrop(event: DragAndDropEvent): Boolean {
                cardGameViewModel.drawCard()
                return true
            }

            override fun onEntered(event: DragAndDropEvent) {
                super.onEntered(event)
                tintColor = CardDragEntered
            }

            override fun onEnded(event: DragAndDropEvent) {
                super.onEntered(event)
                tintColor = DarkFeltGreen
            }

            override fun onExited(event: DragAndDropEvent) {
                super.onEntered(event)
                tintColor = DarkFeltGreen
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .dragAndDropTarget(
                shouldStartDragAndDrop = { event ->
                    event.mimeTypes()
                        .contains(ClipDescription.MIMETYPE_TEXT_PLAIN)
                },
                target = dndTarget
            )
            .border(BorderStroke(2.dp, tintColor))
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        BuildHandMessage(gameUiState)
        Row(
            modifier = Modifier.fillMaxSize().horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy((-50).dp)
        )
        {
            cardGameViewModel.handList.forEach{ cardData ->
                PlayingCard(
                    cardData = cardData,
                    onFlipStateChange = { cardGameViewModel.flipCard(it) }
                )
            }
        }
    }

}

/**
 * Build and display the hand message from the game state.
 */
@Composable
private fun BuildHandMessage(gameUiState: CardGameState) {
    var message = stringResource(R.string.hand_message, gameUiState.userScore)
    if (gameUiState.userScore > 0) message += " -- " + gameUiState.handMessage

    if(gameUiState.isGameOver) message = ""
    Text(
        message,
        modifier = Modifier.animateContentSize()
    )
}