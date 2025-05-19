/**
 * Course work for CIS-135. Final Project 2025 Spring.
 * Author: Antal Berenyi
 * Date: 2025-05-05
 */

package com.aberenyi.play21.data

/**
 * Data class that encapsulates a playing card's suite, rank, rank value, front face and back side.
 */
data class
CardData(
    val suite:String,
    val rank: String,
    val rankValue: Int, // value of the card
    val frontFace: Int, // resource ID for the front face of the card
    val backSide: Int, // resource ID for the back of the card
    var isFaceUp: Boolean = false) // flip state of the card in the game