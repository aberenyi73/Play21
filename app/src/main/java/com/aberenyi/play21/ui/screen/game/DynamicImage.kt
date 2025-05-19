/**
 * Course work for CIS-135. Final Project 2025 Spring.
 * Author: Antal Berenyi
 * Date: 2025-05-05
 */

package com.aberenyi.play21.ui.screen.game

import android.content.ClipData
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.draganddrop.dragAndDropSource
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draganddrop.DragAndDropTransferData
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

/**
 * Dynamically display an image from a resource ID. Clicking the image should call the onImageClick
 * lambda, which should pass the content description of the image to the caller.
 * @param resourceId The resource ID of the image to display.
 * @param contentDescription The content description of the image to display in the footer.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DynamicImage(resourceId:Int, contentDescription:String) {

    Box(modifier = Modifier
        .background(MaterialTheme.colorScheme.background)
        .clip(shape = ShapeDefaults.Medium)
    ) {
        Image(painterResource(id = resourceId), contentDescription = contentDescription,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .dragAndDropSource {
                    detectTapGestures(
                        onPress = {
                            startTransfer(
                                DragAndDropTransferData(
                                    ClipData.newPlainText("card value", contentDescription)
                                )
                            )
                        }
                    )
                }

        )

    }
}