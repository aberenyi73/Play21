/**
 * Course work for CIS-135. Final Project 2025 Spring.
 * Author: Antal Berenyi
 * Date: 2025-05-05
 */

package com.aberenyi.play21.data

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Responsible for interacting with the Room database on behalf of the ViewModel.
 *
 * Provides methods for inserting, querying, and deleting data in the database.
 *
 * The database operations need to be performed on a separate thread to avoid locking the UI.
 * Coroutines are used to perform these operations.
 */
class StatisticsRepository(private val gameStatisticsDao: GameStatisticsDao) {

    /** LiveData that delegates to the Dao. */
    val allStatistics: LiveData<List<GameStatistics>> = gameStatisticsDao.getAllStatistics()

    val statsSummary: LiveData<GameStatisticsSummary> = gameStatisticsDao.getStatisticsSummary()

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insertStatistics(newStatistics: GameStatistics) {
        coroutineScope.launch(Dispatchers.IO) {
            gameStatisticsDao.insert(newStatistics)
        }
    }

    fun deleteAllStatistics() {
        coroutineScope.launch(Dispatchers.IO) {
            gameStatisticsDao.deleteAll()
        }
    }

}

