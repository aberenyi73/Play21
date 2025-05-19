/**
 * Course work for CIS-135. Final Project 2025 Spring.
 * Author: Antal Berenyi
 * Date: 2025-05-05
 */

package com.aberenyi.play21.ui.screen.game

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.aberenyi.play21.data.CardData


/**
 * Dynamically display an image from a resource ID. Clicking the image should call the onImageClick
 * lambda, which should pass the content description of the image to the caller.
 * @param cardData The data for the card to display. Also stores flip state.
 * @param onFlipStateChange The lambda to call when the image is clicked.
 */
@Composable
fun PlayingCard(cardData: CardData, onFlipStateChange: ((Int) -> Unit)) {

    var isFaceUp by remember { mutableStateOf(cardData.isFaceUp) }


    // Define rotation angle
    val rotationAngle by animateFloatAsState(
        targetValue = if (isFaceUp) 0f else 180f,
        label = "rotation",
        animationSpec = androidx.compose.animation.core.tween(durationMillis = 1000)
    )

    val frontImage = painterResource(id = cardData.frontFace)
    val backImage = painterResource(id = cardData.backSide)

    val derivedPainter by remember {
        derivedStateOf {
            if (rotationAngle < 90) frontImage else backImage
        }
    }

    Surface(
        modifier = Modifier
            .clickable {
                    isFaceUp = !isFaceUp
                    onFlipStateChange(cardData.frontFace)
            }
            .graphicsLayer(
                rotationY = rotationAngle, // Apply the rotation animation on Y-axis
                cameraDistance = 24f, // This makes the flip more pronounced
            )
            .padding(2.dp)
            .clip(shape = ShapeDefaults.Large)
    ) {
        Box(
            modifier = Modifier.clip(shape = ShapeDefaults.Large)
        ) {

            Image(
                painter = derivedPainter,
                cardData.toString(),
                contentScale = ContentScale.Fit,
            )
        }
    }
}
