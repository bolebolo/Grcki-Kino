package com.app.grckikino.theming_compose

import androidx.compose.ui.graphics.Color


//light  theme colors
val my_theme_light_primary = Color(0xFF240775)
val my_theme_light_onPrimary = Color(0xFFFFFFFF)
val my_theme_light_primaryContainer = Color(0xFFFFDDB3)
val my_theme_light_onPrimaryContainer = Color(0xFF291800)
val my_theme_light_secondary = Color(0xFFffb617)
val my_theme_light_onSecondary = Color(0xFF240775)
val my_theme_light_secondaryContainer = Color(0xFFFBDEBC)
val my_theme_light_onSecondaryContainer = Color(0xFF271904)
val my_theme_light_tertiary = Color(0xFF51643F)
val my_theme_light_onTertiary = Color(0xFFFFFFFF)
val my_theme_light_tertiaryContainer = Color(0xFFD4EABB)
val my_theme_light_onTertiaryContainer = Color(0xFF102004)
val my_theme_light_error = Color(0xFFBF152C)
val my_theme_light_errorContainer = Color(0xFFFFDAD6)
val my_theme_light_onError = Color(0xFFFFFFFF)
val my_theme_light_onErrorContainer = Color(0xFF410002)
val my_theme_light_background = Color(0xFFFFFFFF)
val my_theme_light_onBackground = Color(0xFF000000)
val my_theme_light_surface = Color(0xFFFFFFFF)
val my_theme_light_onSurface = Color(0xFF000000)
val my_theme_light_surfaceVariant = Color(0xFFF0E0CF)
val my_theme_light_onSurfaceVariant = Color(0xFF4F4539)
val my_theme_light_outline = Color(0xFF240775)
val my_theme_light_inverseOnSurface = Color(0xFFF9EFE7)
val my_theme_light_inverseSurface = Color(0xFF34302A)
val my_theme_light_inversePrimary = Color(0xFFFFB951)
val my_theme_light_shadow = Color(0xFF000000)
val my_theme_light_surfaceTint = Color(0xFF825500)
val my_theme_light_outlineVariant = Color(0xFFD3C4B4)
val my_theme_light_scrim = Color(0xFF000000)


//dark theme colors
val my_theme_dark_primary = Color(0xFF240775)
val my_theme_dark_onPrimary = Color(0xFFFFFFFF)
val my_theme_dark_primaryContainer = Color(0xFF633F00)
val my_theme_dark_onPrimaryContainer = Color(0xFFFFDDB3)
val my_theme_dark_secondary = Color(0xFFffb617)
val my_theme_dark_onSecondary = Color(0xFF000000)
val my_theme_dark_secondaryContainer = Color(0xFF56442A)
val my_theme_dark_onSecondaryContainer = Color(0xFFFBDEBC)
val my_theme_dark_tertiary = Color(0xFFB8CEA1)
val my_theme_dark_onTertiary = Color(0xFF243515)
val my_theme_dark_tertiaryContainer = Color(0xFF3A4C2A)
val my_theme_dark_onTertiaryContainer = Color(0xFFD4EABB)
val my_theme_dark_error = Color(0xFFBF152C)
val my_theme_dark_errorContainer = Color(0xFF93000A)
val my_theme_dark_onError = Color(0xFF000000)
val my_theme_dark_onErrorContainer = Color(0xFFFFDAD6)
val my_theme_dark_background = Color(0xFF000000)
val my_theme_dark_onBackground = Color(0xFFFFFFFF)
val my_theme_dark_surface = Color(0xFF000000)
val my_theme_dark_onSurface = Color(0xFFFFFFFF)
val my_theme_dark_surfaceVariant = Color(0xFF4F4539)
val my_theme_dark_onSurfaceVariant = Color(0xFFD3C4B4)
val my_theme_dark_outline = Color(0xFFffb617)
val my_theme_dark_inverseOnSurface = Color(0xFF1F1B16)
val my_theme_dark_inverseSurface = Color(0xFFEAE1D9)
val my_theme_dark_inversePrimary = Color(0xFF825500)
val my_theme_dark_shadow = Color(0xFF000000)
val my_theme_dark_surfaceTint = Color(0xFFFFB951)
val my_theme_dark_outlineVariant = Color(0xFF4F4539)
val my_theme_dark_scrim = Color(0xFF000000)


val seed = Color(0xFF825500)

val balls_color_1_10 = Color(0xFFFDE300)
val balls_color_11_20 = Color(0xFFFF802B)
val balls_color_21_30 = Color(0xFFFF1E00)
val balls_color_31_40 = Color(0xFFF160D1)
val balls_color_41_50 = Color(0xFF9F06FF)
val balls_color_51_60 = Color(0xFF54F8F2)
val balls_color_61_70 = Color(0xFF24F11B)
val balls_color_71_80 = Color(0xFF093ED8)


fun getColorForNumber(number: Int): Color {
    return when {
        number in 1..10 -> balls_color_1_10
        number in 11..20 -> balls_color_11_20
        number in 21..30 -> balls_color_21_30
        number in 31..40 -> balls_color_31_40
        number in 41..50 -> balls_color_41_50
        number in 51..60 -> balls_color_51_60
        number in 61..70 -> balls_color_61_70
        number in 71..80 -> balls_color_71_80
        else -> throw IllegalArgumentException("Broj mora biti između 1 i 80")
    }
}
