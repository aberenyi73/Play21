/**
 * Course work for CIS-135. Final Project 2025 Spring.
 * Author: Antal Berenyi
 * Date: 2025-05-05
 */

package com.aberenyi.play21.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

/**
 * Database access object to access the Game Statistics database.
 * Insert, retrieve, and delete game statistics.
 */
@Dao
interface GameStatisticsDao {

    @Insert
    fun insert(gameStatistics: GameStatistics)

    @Query("SELECT * FROM game_statistics")
    fun getAllStatistics(): LiveData<List<GameStatistics>>

    @Query("SELECT * FROM game_statistics WHERE user_score = :score")
    fun findUserScore(score: Int): List<GameStatistics>

    @Query("SELECT * FROM game_statistics WHERE user_score = :userscore AND machine_score = :machineScore")
    fun findUserMachineScore(userscore: Int, machineScore: Int): List<GameStatistics>

    @Query("SELECT * FROM game_statistics WHERE machine_score = :machineScore")
    fun findMachineScore(machineScore: Int): List<GameStatistics>

    @Query("DELETE FROM game_statistics WHERE user_score = :score")
    fun deleteUserScore(score: Int)

    @Query("DELETE FROM game_statistics WHERE machine_score = :score")
    fun deleteMachineScore(score: Int)

    @Query("DELETE FROM game_statistics WHERE user_score = :userScore AND machine_score = :machineScore")
    fun deleteUserMachineScore(userScore: Int, machineScore: Int)


    /**
     * Clear all rows from the database.
     */
    @Query("DELETE FROM game_statistics")
    fun deleteAll()

    @Query("SELECT COUNT(*) FROM game_statistics")
    fun getRowCount(): Int


    @Query("SELECT \n" +
            "(SELECT COUNT(*)  FROM game_statistics) AS gameCount,\n" +
            "(SELECT COUNT(*)  FROM game_statistics where winner = 'Machine') AS machineWins,\n" +
            "(SELECT COUNT(*)  FROM game_statistics where winner = 'Tie') AS tieWins,\n" +
            "(SELECT COUNT(*)  FROM game_statistics where winner = 'User') AS userWins")
    fun getStatisticsSummary(): LiveData<GameStatisticsSummary>




}