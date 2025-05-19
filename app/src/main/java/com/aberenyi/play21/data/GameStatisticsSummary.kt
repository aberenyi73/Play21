/**
 * Course work for CIS-135. Final Project 2025 Spring.
 * Author: Antal Berenyi
 * Date: 2025-05-05
 */

package com.aberenyi.play21.data

/**
 * Data class to store game statistics summary.
 */
data class GameStatisticsSummary (
    val userWins: Int,
    val machineWins: Int,
    val tieWins: Int,
    val gameCount: Int
)