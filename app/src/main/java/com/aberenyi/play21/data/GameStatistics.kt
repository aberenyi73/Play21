/**
 * Course work for CIS-135. Final Project 2025 Spring.
 * Author: Antal Berenyi
 * Date: 2025-05-05
 */

package com.aberenyi.play21.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aberenyi.play21.mvvm.GameRules
import java.util.Date

/**
 * Entity class to store game statistics.
 */
@Entity(tableName = "game_statistics")
class GameStatistics {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    var game_date: Date = Date()

    var user_score: Int = 0

    var machine_score: Int = 0

    var winner: GameRules.Winner = GameRules.Winner.Tie

    constructor()

    constructor(gameDate: Date, userScore: Int, machineScore: Int, winner: GameRules.Winner) {
        this.game_date = gameDate
        this.user_score = userScore
        this.machine_score = machineScore
        this.winner = winner
    }

}