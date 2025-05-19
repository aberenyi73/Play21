/**
 * Course work for CIS-135. Final Project 2025 Spring.
 * Author: Antal Berenyi
 * Date: 2025-05-05
 */

package com.aberenyi.play21.mvvm

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.aberenyi.play21.data.CardData
import com.aberenyi.play21.data.GameStatistics
import com.aberenyi.play21.data.GameStatisticsSummary
import com.aberenyi.play21.data.StatisticsRepository
import com.aberenyi.play21.data.StatisticsRoomDatabase
import com.aberenyi.play21.data.fullDeck
import com.aberenyi.play21.mvvm.GameRules.Companion.getWinner
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Date

/**
 * The view model for the game.
 * It facilitates the communication between the UI and the data layer.
 * Manage the game state and logic. Manage hand, score, and turn state. Calculate final score.
 */
class CardGameViewModel(application: Application) : ViewModel() {

    // Game UI state
    private val _uiState = MutableStateFlow(CardGameState())

    //The asStateFlow() makes this mutable state flow a read-only state flow.
    val uiState: StateFlow<CardGameState> = _uiState.asStateFlow()

    // Room database objects
    private val repository: StatisticsRepository
    val statsSummary: LiveData<GameStatisticsSummary>
    val allStatistics: LiveData<List<GameStatistics>>

    // This will be initialized when we start a new game.
    private lateinit var drawPile: MutableList<CardData>

    // make it a state to be observed by the UI
    var handList by mutableStateOf(mutableListOf<CardData>())
        private set

    init {
        resetGame()

        val statsDB = StatisticsRoomDatabase.getInstance(application)
        val statsDao = statsDB.gameStatisticsDao()

        repository = StatisticsRepository(statsDao)
        statsSummary = repository.statsSummary
        allStatistics = repository.allStatistics

    }


    /**
     * Insert a game statistic in the database, delegating to the repository.
     */
    private fun insertStatistics(newStatistics: GameStatistics) {
        repository.insertStatistics(newStatistics)
    }

    fun deleteAllStatistics() {
        repository.deleteAllStatistics()
    }

    /**
     * Draw a card from the draw pile and add it to the hand. Update the user score.
     */
    fun drawCard() {
        if (drawPile.isNotEmpty()) {
            val cardDrawn = drawPile.first()
            drawPile = drawPile.drop(1).toMutableList()
            handList.add(cardDrawn)
            updateUserScore()
        }
    }

    /**
     * @param id The frontFace image ID of the card to flip.
     */
    fun flipCard(id: Int) {
        try {
            val cd = handList.first { it.frontFace == id }
            cd.isFaceUp = cd.isFaceUp.not()
        } catch (e: NoSuchElementException) {
            return
        }
    }

    /**
     * Handle control over to the machine, play it's turn, and update the game state.
     */
    fun endTurn() {
        updateUserScore()
        if (!_uiState.value.isGameOver) {
            playMachine()
        }
        calculateFinalScore()
        if(_uiState.value.isSaved.not()) {
            insertStatistics(GameStatistics(Date(), _uiState.value.userScore, _uiState.value.machineScore, _uiState.value.winner))
            _uiState.value = _uiState.value.copy(isSaved = true)
        }
    }

    /**
     * Simulate a machine turn. Machine is dumb, will keep drawing until score is 19 or less.
     */
    private fun playMachine() {
        var machineScore = 0
        var cardDrawn: CardData

        val machineHandList = mutableListOf<CardData>()

        while (drawPile.isNotEmpty() && (machineScore in 0..19)) {

            cardDrawn = drawPile.first()
            drawPile = drawPile.drop(1).toMutableList()
            machineHandList.add(cardDrawn)

            machineScore = when(machineHandList.filter { it.rankValue == 11 }.size == 2) {
                true -> 21 // two deuces
                false -> machineHandList.sumOf { it.rankValue }
            }
        }

        // update machine score
        _uiState.value = _uiState.value.copy(machineScore = machineScore)

    }

    /**
     * Update the user score based on the current hand and according to the rules of the game.
     */
    private fun updateUserScore() {

        val score = when(handList.filter { it.rankValue == 11 }.size == 2) {
            true -> 21 // two deuces
            false -> handList.sumOf { it.rankValue }
        }

        _uiState.value = when(score) {
            in 0..14 -> _uiState.value.copy(userScore = score, handMessage = "Draw card")
            in 15..20 -> _uiState.value.copy(userScore = score, handMessage = "Playable")
            21 -> _uiState.value.copy(userScore = score, handMessage = "You have 21, Stop now")
            // over 21
            else -> _uiState.value.copy(userScore = score, handMessage = "Over 21, End turn")
        }

    }

    /**
     * Calculate the final score of the game based on both user and machine scores.
     */
    private fun calculateFinalScore() {

        val winner = getWinner(_uiState.value.userScore, _uiState.value.machineScore)

        _uiState.value = when(
            winner
        ) {
            GameRules.Winner.User -> _uiState.value.copy(handMessage = "You won", isGameOver = true, winner = GameRules.Winner.User)
            GameRules.Winner.Machine -> _uiState.value.copy(handMessage = "You lost", isGameOver = true, winner = GameRules.Winner.Machine)
            GameRules.Winner.Tie -> _uiState.value.copy(handMessage = "A Tie", isGameOver = true, winner = GameRules.Winner.Tie)
        }

    }

    /**
     * Start a new game by resetting the game state and deck.
     */
    fun resetGame() {
        handList.clear()
        drawPile = fullDeck.shuffled().toMutableList()
        _uiState.value = CardGameState()

    }

}

