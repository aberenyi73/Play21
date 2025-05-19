/**
 * Course work for CIS-135. Final Project 2025 Spring.
 * Author: Antal Berenyi
 * Date: 2025-05-05
 */

package com.aberenyi.play21.ui.screen.stats

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.aberenyi.play21.R
import com.aberenyi.play21.data.GameStatisticsSummary
import com.aberenyi.play21.mvvm.CardGameViewModel

/**
 * Responsible for displaying the statistics summary and the reset button.
 */
@Composable
fun StatisticsSummary(
    statsSummary: GameStatisticsSummary,
    viewModel: CardGameViewModel,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth() // Only take full width, not height
            //.padding(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.stats),
                style = MaterialTheme.typography.headlineSmall,
                fontStyle = FontStyle.Italic,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "Games Played:    - " + statsSummary.gameCount.toString() + " -",
                style = MaterialTheme.typography.titleLarge,
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        PlayerStats(
            R.string.user,
            statsSummary.userWins,
            statsSummary.machineWins,
            statsSummary.tieWins
        )

        Spacer(modifier = Modifier.height(16.dp))

        PlayerStats(
            R.string.machine,
            statsSummary.machineWins,
            statsSummary.userWins,
            statsSummary.tieWins
        )

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        )
        {
            ResetStatsWithConfirmationButton(
                text = "Reset Stats",
                confirmTitle = "Reset Confirmation",
                confirmMessage = "Are you sure you want to reset the stats? This action cannot be undone.",
                onConfirm = {
                    // Handle the confirmed action here
                    // For example: deleteItem(itemId)
                    viewModel.deleteAllStatistics()
                }
            )
        }
    }
}