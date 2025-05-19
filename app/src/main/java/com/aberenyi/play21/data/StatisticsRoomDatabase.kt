/**
 * Course work for CIS-135. Final Project 2025 Spring.
 * Author: Antal Berenyi
 * Date: 2025-05-05
 */

package com.aberenyi.play21.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

/**
 * Implements the Room Database instance that contains the GameStatistics table
 */
@Database(entities = [GameStatistics::class], version = 1, exportSchema = false)
@TypeConverters(GameTypeConverters::class)
abstract class StatisticsRoomDatabase : RoomDatabase() {

    abstract fun gameStatisticsDao(): GameStatisticsDao

    companion object {

        @Volatile
        private var INSTANCE: StatisticsRoomDatabase? = null

        fun getInstance(context: Context): StatisticsRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(context, StatisticsRoomDatabase::class.java,"game_statistics_database",)
                    .fallbackToDestructiveMigration(dropAllTables = true).build()
                    .also { INSTANCE = it }
            }
        }
    }
}