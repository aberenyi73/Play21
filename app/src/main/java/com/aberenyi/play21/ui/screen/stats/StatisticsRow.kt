/**
 * Course work for CIS-135. Final Project 2025 Spring.
 * Author: Antal Berenyi
 * Date: 2025-05-05
 */

package com.aberenyi.play21.ui.screen.stats

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aberenyi.play21.mvvm.GameRules
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * One row of the statistics table with the game statistics.
 *
 * @param date The date of the game
 * @param userScore The user score
 * @param machineScore The machine score
 * @param winner The winner of the game
 */
@Composable
fun StatisticsRow(date: Date, userScore: Int, machineScore: Int, winner: GameRules.Winner) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)

    ) {
        Text(text = format(date), modifier = Modifier.weight(0.3f))
        Text(text = userScore.toString(), modifier = Modifier.weight(0.2f))
        Text(text = machineScore.toString(), modifier = Modifier.weight(0.2f))
        Text(text = winner.toString(), modifier = Modifier.weight(0.2f))
    }
}

private fun format(date: Date): String {
    val ds = SimpleDateFormat("MMM/dd/yyyy", Locale.getDefault()).format(date)
    return ds
}