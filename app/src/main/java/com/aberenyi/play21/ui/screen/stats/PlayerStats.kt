/**
 * Course work for CIS-135. Final Project 2025 Spring.
 * Author: Antal Berenyi
 * Date: 2025-05-05
 */

package com.aberenyi.play21.ui.screen.stats

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import com.aberenyi.play21.R

/**
 * Template for displaying player a player and wins, losses and ties.
 */
@Composable
fun PlayerStats(playerResId: Int, wins: Int, losses: Int, ties: Int) {

    Row(
        Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.secondary),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            stringResource(playerResId) + ": ",
            style = MaterialTheme.typography.titleLarge,
            fontStyle = FontStyle.Italic,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            modifier = Modifier.weight(0.3f)
        )
        Text(
            stringResource(R.string.wins, wins),
            modifier = Modifier.weight(0.25f)
        )

        Text(
            stringResource(R.string.losses, losses),
            modifier = Modifier.weight(0.25f)
        )

        Text(
            stringResource(R.string.ties, ties),
            modifier = Modifier.weight(0.25f)
        )
    }

}