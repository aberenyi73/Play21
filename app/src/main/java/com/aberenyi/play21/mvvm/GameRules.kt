/**
 * Course work for CIS-135. Final Project 2025 Spring.
 * Author: Antal Berenyi
 * Date: 2025-05-05
 */

package com.aberenyi.play21.mvvm


/**
 * This class contains the rules of the game. The rules are defined in the companion object to
 * implement as a static method.
 */
class GameRules {

    /**
     * Enum class to represent the possible winner of the game.
     */
    enum class Winner {
        User, Machine, Tie
    }

    companion object {

        private const val MIN_SCORE = 15
        private const val WIN_SCORE = 21
        private const val BUST_SCORE = 100000

        /**
         * Determine the winner of the game based on the user and machine scores. Apply the rules of
         * the game BlackJack.
         *
         * @param userScore The score of the user.
         * @param machScore The score of the machine.
         * @return The winner of the game: User, Machine, or Tie.
         */
        fun getWinner(userScore :Int, machScore:Int): Winner {
            return if ((userScore < MIN_SCORE || userScore > WIN_SCORE) && (machScore < MIN_SCORE || machScore > WIN_SCORE)) {
                Winner.Tie
            } else {
                val userDist = if(userScore > WIN_SCORE) BUST_SCORE else WIN_SCORE - userScore
                val machDist = if(machScore > WIN_SCORE) BUST_SCORE else WIN_SCORE - machScore

                if (userDist > machDist) {
                    Winner.Machine
                } else if (userDist < machDist) {
                    Winner.User
                } else {
                    Winner.Tie
                }
            }
        }
    }

}