package com.example.wanderway.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color   // ✅ Add this line

private val WanderwayColorScheme = lightColorScheme(
    primary = PrimaryBlue,
    secondary = AccentYellow,
    background = Color(0xFFFDFDFD)
)

@Composable
fun WanderwayTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = WanderwayColorScheme,
        typography = androidx.compose.material3.Typography(),
        content = content
    )
}
