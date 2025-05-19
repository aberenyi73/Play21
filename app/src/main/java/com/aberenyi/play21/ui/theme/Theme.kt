/**
 * Course work for CIS-135. Final Project 2025 Spring.
 * Author: Antal Berenyi
 * Date: 2025-05-05
 */

package com.aberenyi.play21.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext


// Light theme colors
private val CardTableLightColorScheme = lightColorScheme(
    primary = FeltGreen,
    onPrimary = CreamWhite,
    primaryContainer = LightFeltGreen,
    onPrimaryContainer = CreamWhite,
    secondary = GoldAccent,
    onSecondary = CardBlack,
    secondaryContainer = Color(0xFFEAD79B), // Light gold
    onSecondaryContainer = CardBlack,
    tertiary = CardRed,
    onTertiary = CreamWhite,
    tertiaryContainer = Color(0xFFFFDAD6), // Light red
    onTertiaryContainer = CardRed,
    background = FeltGreen, // Light cream background
    onBackground = CardBlack,
    surface = CreamWhite,
    onSurface = CardBlack,
    surfaceVariant = Color(0xFFE1DDD3), // Variant of cream
    onSurfaceVariant = Color(0xFF4C4639), // Dark beige for text on variants
    inverseSurface = DarkFeltGreen,
    inverseOnSurface = CreamWhite,
    error = Color(0xFFBA1A1A),
    onError = Color.White
)

// Dark theme colors
private val CardTableDarkColorScheme = darkColorScheme(
    primary = LightFeltGreen,
    onPrimary = CreamWhite,
    primaryContainer = FeltGreen,
    onPrimaryContainer = CreamWhite,
    secondary = GoldAccent,
    onSecondary = CardBlack,
    secondaryContainer = Color(0xFF5E4D00), // Darker gold
    onSecondaryContainer = GoldAccent,
    tertiary = Color(0xFFFFB4AB), // Lighter red for dark theme
    onTertiary = Color(0xFF690005),
    tertiaryContainer = Color(0xFF93000A), // Darker red
    onTertiaryContainer = Color(0xFFFFB4AB),
    background = DarkFeltGreen,
    onBackground = CreamWhite,
    surface = Color(0xFF0F4429), // Slightly lighter than background for contrast
    onSurface = CreamWhite,
    surfaceVariant = Color(0xFF0E3A23), // Variation of dark felt green
    onSurfaceVariant = Color(0xFFCFCBBE), // Light beige for text on variants
    inverseSurface = Color(0xFFE1DDD3), // Light cream
    inverseOnSurface = CardBlack,
    error = Color(0xFFFFB4AB),
    onError = Color(0xFF690005)
)


@Composable
fun Play21Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> CardTableDarkColorScheme
        else -> CardTableLightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}