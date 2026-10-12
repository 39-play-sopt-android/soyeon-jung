package org.sopt.play.core.designsystem.theme


import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = Black,
    background = White,
    surface = White,
    onBackground = Black,
    onSurface = Black
)

@Composable
fun PlaySoptTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        content = content
    )
}
