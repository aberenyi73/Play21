/**
 * Course work for CIS-135. Final Project 2025 Spring.
 * Author: Antal Berenyi
 * Date: 2025-05-05
 */

package com.aberenyi.play21.data

import com.aberenyi.play21.R

val deckColor: Int = R.drawable.blue

/**
 * A full deck of playing cards.
 */
val fullDeck: List<CardData> = listOf(
    CardData("Weli", "VI", 6, R.drawable.weli, deckColor),

    CardData("Acorn", "VII", 7, R.drawable.a_07, deckColor),
    CardData("Acorn", "VIII", 8, R.drawable.a_08, deckColor),
    CardData("Acorn", "IX", 9, R.drawable.a_09, deckColor),
    CardData("Acorn", "X", 10, R.drawable.a_10, deckColor),
    CardData("Acorn", "Unter", 2, R.drawable.a_11, deckColor),
    CardData("Acorn", "Ober", 3, R.drawable.a_12, deckColor),
    CardData("Acorn", "Konig", 4, R.drawable.a_13, deckColor),
    CardData("Acorn", "Ace", 11, R.drawable.a_14, deckColor),

    CardData("Bell", "VII", 7, R.drawable.b_07, deckColor),
    CardData("Bell", "VIII", 8, R.drawable.b_08, deckColor),
    CardData("Bell", "IX", 9, R.drawable.b_09, deckColor),
    CardData("Bell", "X", 10, R.drawable.b_10, deckColor),
    CardData("Bell", "Unter", 2, R.drawable.b_11, deckColor),
    CardData("Bell", "Ober", 3, R.drawable.b_12, deckColor),
    CardData("Bell", "Konig", 4, R.drawable.b_13, deckColor),
    CardData("Bell", "Ace", 11, R.drawable.b_14, deckColor),

    CardData("Heart", "VII", 7, R.drawable.h_07, deckColor),
    CardData("Heart", "VIII", 8, R.drawable.h_08, deckColor),
    CardData("Heart", "IX", 9, R.drawable.h_09, deckColor),
    CardData("Heart", "X", 10, R.drawable.h_10, deckColor),
    CardData("Heart", "Unter", 2, R.drawable.h_11, deckColor),
    CardData("Heart", "Ober", 3, R.drawable.h_12, deckColor),
    CardData("Heart", "Konig", 4, R.drawable.h_13, deckColor),
    CardData("Heart", "Ace", 11, R.drawable.h_14, deckColor),

    CardData("Leaf", "VII", 7, R.drawable.l_07, deckColor),
    CardData("Leaf", "VIII", 8, R.drawable.l_08, deckColor),
    CardData("Leaf", "IX", 9, R.drawable.l_09, deckColor),
    CardData("Leaf", "X", 10, R.drawable.l_10, deckColor),
    CardData("Leaf", "Unter", 2, R.drawable.l_11, deckColor),
    CardData("Leaf", "Ober", 3, R.drawable.l_12, deckColor),
    CardData("Leaf", "Konig", 4, R.drawable.l_13, deckColor),
    CardData("Leaf", "Ace", 11, R.drawable.l_14, deckColor),

    )

