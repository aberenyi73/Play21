/**
 * Course work for CIS-135. Final Project 2025 Spring.
 * Author: Antal Berenyi
 * Date: 2025-05-05
 */

package com.aberenyi.play21.ui.screen.stats

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ResetStatsWithConfirmationButton(
    text: String = "Click Me",
    confirmTitle: String = "Confirm Action",
    confirmMessage: String = "Are you sure you want to perform this action?",
    onConfirm: () -> Unit = {}
) {
    var showDialog by remember { mutableStateOf(false) }

    // Custom elevated button with red border
    ElevatedButton(
        onClick = { showDialog = true },
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.tertiary),
        colors = ButtonDefaults.elevatedButtonColors(
            containerColor = MaterialTheme.colorScheme.onTertiary,
            contentColor = MaterialTheme.colorScheme.tertiary
        ),
        elevation = ButtonDefaults.elevatedButtonElevation(
            defaultElevation = 6.dp,
            pressedElevation = 8.dp,
            hoveredElevation = 10.dp,
            focusedElevation = 10.dp
        ),
        modifier = Modifier.padding(16.dp)
    ) {
        Text(text)
    }

    // Confirmation dialog
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(confirmTitle) },
            text = { Text(confirmMessage) },
            confirmButton = {
                TextButton(
                    onClick = {
                        onConfirm()
                        showDialog = false
                    }
                ) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showDialog = false }
                ) {
                    Text("Cancel")
                }
            }
        )
    }
}

// Example usage
@Composable
@Preview
fun ButtonExample() {
    ResetStatsWithConfirmationButton(
        text = "Delete Item",
        confirmTitle = "Delete Confirmation",
        confirmMessage = "Are you sure you want to delete this item? This action cannot be undone.",
        onConfirm = {
            // Handle the confirmed action here
            // For example: deleteItem(itemId)
        }
    )
}