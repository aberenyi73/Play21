/**
 * Course work for CIS-135. Final Project 2025 Spring.
 * Author: Antal Berenyi
 * Date: 2025-05-05
 */

package com.aberenyi.play21.ui.screen.stats

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Title row for the game statistics table.
 */
@Composable
fun TitleRow(head1: String, head2: String, head3: String, head4: String) {
    Row(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.tertiary)
            .fillMaxWidth()
            .padding(5.dp)

    ) {
        Text(text = head1, color = Color.White, modifier = Modifier.weight(0.3f))
        Text(text = head2, color = Color.White, modifier = Modifier.weight(0.2f))
        Text(text = head3, color = Color.White, modifier = Modifier.weight(0.2f))
        Text(text = head4, color = Color.White, modifier = Modifier.weight(0.2f))
    }
}