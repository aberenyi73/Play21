/**
 * Course work for CIS-135. Final Project 2025 Spring.
 * Author: Antal Berenyi
 * Date: 2025-05-05
 */

package com.aberenyi.play21.ui.screen.rules

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.aberenyi.play21.R
import com.aberenyi.play21.getDictionaryValueFromXml

/**
 * A simple screen for displaying the game rules from XML resources.
 */
@Composable
fun RulesScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize().padding(16.dp),
        contentAlignment = Alignment.TopStart
    ) {
        GameRules()
    }

}

/**
 * Simply display the game rules from XML resources.
 */
@Composable
private fun GameRules() {
    Column(
        Modifier.verticalScroll(rememberScrollState()),
        ) {
        Text(
            stringResource(R.string.rules),
            style = MaterialTheme.typography.headlineSmall,
            fontStyle = FontStyle.Italic,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            getDictionaryValueFromXml(LocalContext.current, R.xml.game_info, "game_rules"),
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}