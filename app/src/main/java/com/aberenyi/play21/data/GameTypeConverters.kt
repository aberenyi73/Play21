/**
 * Course work for CIS-135. Final Project 2025 Spring.
 * Author: Antal Berenyi
 * Date: 2025-05-05
 */

package com.aberenyi.play21.data

import androidx.room.TypeConverter
import com.aberenyi.play21.mvvm.GameRules
import java.util.Date

/**
 *  Create a Type Converter for Date objects, to store field types not directly supported by Room.
 */
class GameTypeConverters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }


    @TypeConverter
    fun fromWinnerStatus(value: String?): GameRules.Winner? {
        return value?.let { GameRules.Winner.valueOf(it) }
    }

    @TypeConverter
    fun winnerStatusToString(winnerStatus: GameRules.Winner?): String? {
        return winnerStatus?.toString()
    }
}