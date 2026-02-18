package org.depinfo.clientmobileomnisus.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = Color(0xFFFF9800),
    onPrimary = Color.White,
    secondary = Color(0xFF3F51B5),
    onSecondary = Color.Black
)

@Composable
fun OmnisusTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        content = content
    )
}
