package com.app.grckikino.theming_compose

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme


@Composable
fun AppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (!useDarkTheme) {
        LightColors
    } else {
        DarkColors
    }

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}





private val LightColors = lightColorScheme(
    primary = my_theme_light_primary,
    onPrimary = my_theme_light_onPrimary,
    primaryContainer = my_theme_light_primaryContainer,
    onPrimaryContainer = my_theme_light_onPrimaryContainer,
    secondary = my_theme_light_secondary,
    onSecondary = my_theme_light_onSecondary,
    secondaryContainer = my_theme_light_secondaryContainer,
    onSecondaryContainer = my_theme_light_onSecondaryContainer,
    tertiary = my_theme_light_tertiary,
    onTertiary = my_theme_light_onTertiary,
    tertiaryContainer = my_theme_light_tertiaryContainer,
    onTertiaryContainer = my_theme_light_onTertiaryContainer,
    error = my_theme_light_error,
    errorContainer = my_theme_light_errorContainer,
    onError = my_theme_light_onError,
    onErrorContainer = my_theme_light_onErrorContainer,
    background = my_theme_light_background,
    onBackground = my_theme_light_onBackground,
    surface = my_theme_light_surface,
    onSurface = my_theme_light_onSurface,
    surfaceVariant = my_theme_light_surfaceVariant,
    onSurfaceVariant = my_theme_light_onSurfaceVariant,
    outline = my_theme_light_outline,
    inverseOnSurface = my_theme_light_inverseOnSurface,
    inverseSurface = my_theme_light_inverseSurface,
    inversePrimary = my_theme_light_inversePrimary,
    surfaceTint = my_theme_light_surfaceTint,
    outlineVariant = my_theme_light_outlineVariant,
    scrim = my_theme_light_scrim,
)


private val DarkColors = darkColorScheme(
    primary = my_theme_dark_primary,
    onPrimary = my_theme_dark_onPrimary,
    primaryContainer = my_theme_dark_primaryContainer,
    onPrimaryContainer = my_theme_dark_onPrimaryContainer,
    secondary = my_theme_dark_secondary,
    onSecondary = my_theme_dark_onSecondary,
    secondaryContainer = my_theme_dark_secondaryContainer,
    onSecondaryContainer = my_theme_dark_onSecondaryContainer,
    tertiary = my_theme_dark_tertiary,
    onTertiary = my_theme_dark_onTertiary,
    tertiaryContainer = my_theme_dark_tertiaryContainer,
    onTertiaryContainer = my_theme_dark_onTertiaryContainer,
    error = my_theme_dark_error,
    errorContainer = my_theme_dark_errorContainer,
    onError = my_theme_dark_onError,
    onErrorContainer = my_theme_dark_onErrorContainer,
    background = my_theme_dark_background,
    onBackground = my_theme_dark_onBackground,
    surface = my_theme_dark_surface,
    onSurface = my_theme_dark_onSurface,
    surfaceVariant = my_theme_dark_surfaceVariant,
    onSurfaceVariant = my_theme_dark_onSurfaceVariant,
    outline = my_theme_dark_outline,
    inverseOnSurface = my_theme_dark_inverseOnSurface,
    inverseSurface = my_theme_dark_inverseSurface,
    inversePrimary = my_theme_dark_inversePrimary,
    surfaceTint = my_theme_dark_surfaceTint,
    outlineVariant = my_theme_dark_outlineVariant,
    scrim = my_theme_dark_scrim,
)
