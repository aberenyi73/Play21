/**
 * Course work for CIS-135. Final Project 2025 Spring.
 * Author: Antal Berenyi
 * Date: 2025-05-05
 */

package com.aberenyi.play21.ui.screen.stats

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.aberenyi.play21.R
import com.aberenyi.play21.data.GameStatisticsSummary
import com.aberenyi.play21.mvvm.CardGameViewModel

/**
 * Screen and layout definition for displaying game statistics.
 * The top of the screen is a summary of the game statistics with a reset button.
 * Below that is a table of the game statistics.
 *
 * @param modifier The modifier to apply to the screen
 * @param viewModel The view model to use to get historical stats from the database and to reset it.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StatScreen(modifier: Modifier = Modifier, viewModel: CardGameViewModel) {

    val statsSummary by viewModel.statsSummary.observeAsState(GameStatisticsSummary(0,0,0,0))
    val allStatistics by viewModel.allStatistics.observeAsState(listOf())

    val configuration = LocalConfiguration.current
    val isPortrait = configuration.orientation == Configuration.ORIENTATION_PORTRAIT

    if(isPortrait) {

        LazyColumn(
            Modifier.padding(12.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top

        ) {

            stickyHeader {
                Column(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background) // make it not transparent
                        .fillMaxWidth()
                        .zIndex(1f) // ensure it's drawn above the list items
                ) {

                    StatisticsSummary(statsSummary, viewModel)
                    HorizontalDivider()
                    Spacer(modifier = Modifier.height(16.dp))
                    TitleRow(
                        stringResource(R.string.game_date),
                        stringResource(R.string.user),
                        stringResource(R.string.machine),
                        stringResource(R.string.game_winner)
                    )
                }
            }

            items(allStatistics)
            { item ->
                StatisticsRow(
                    item.game_date,
                    item.user_score,
                    item.machine_score,
                    item.winner
                )
            }
        }
    } else {
        Row(Modifier.fillMaxSize())
        {
            // Left column for statistics summary
            Column(
                Modifier
                    .weight(0.5f)
                    .fillMaxHeight()  // Make sure it takes full height
                    .padding(12.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Top
            ) {
                // Add a scroll wrapper if needed
                StatisticsSummary(statsSummary, viewModel)
            }

            LazyColumn(
                Modifier.padding(12.dp).weight(0.5f),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top

            ) {

                stickyHeader {
                    Column(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.background) // make it not transparent
                            .fillMaxWidth()
                            .zIndex(1f) // ensure it's drawn above the list items
                    ) {
                        TitleRow(
                            stringResource(R.string.game_date),
                            stringResource(R.string.user),
                            stringResource(R.string.machine),
                            stringResource(R.string.game_winner)
                        )
                    }
                }

                items(allStatistics)
                { item ->
                    StatisticsRow(
                        item.game_date,
                        item.user_score,
                        item.machine_score,
                        item.winner
                    )
                }
            }



        }

    }
}


