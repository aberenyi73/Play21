/**
 * Course work for CIS-135. Final Project 2025 Spring.
 * Author: Antal Berenyi
 * Date: 2025-05-05
 */

package com.aberenyi.play21.mvvm

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel

/**
 * Factory class for creating [CardGameViewModel].
 */
class CardGameModelFactory(private val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CardGameViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CardGameViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

/**
 * Convenience function to create a [CardGameViewModel] instance.
 */
object ViewModelUtils {

    /**
     * Create a [CardGameViewModel], initializing it with the application [Application] and the local context,
     * utilizing the factory class [CardGameModelFactory].
     */
    @Composable
    fun cardGameViewModel(): CardGameViewModel {

        val owner = LocalViewModelStoreOwner.current
        owner?.let {
            return viewModel(
                it,
                "CardGameViewModel",
                // obtain a reference to the application context
                CardGameModelFactory(LocalContext.current.applicationContext as Application)
            )

        }
        throw IllegalStateException("View model is null")

    }
}