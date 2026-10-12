package org.sopt.play.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.core.designsystem.theme.Black
import org.sopt.play.core.designsystem.theme.Gray1
import org.sopt.play.core.designsystem.theme.Gray3
import org.sopt.play.core.designsystem.theme.PlaySoptTheme
import org.sopt.play.core.designsystem.theme.SoptTypography
import org.sopt.play.core.designsystem.theme.White

@Composable
fun SoptButton(
    text: String,
    enabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(min=52.dp)
            .clip(shape = CircleShape)
            .background(if (enabled) Black else Gray1)
            .clickable(enabled = enabled, onClick = onClick)
            .padding(vertical = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = SoptTypography.sb14,
            color = if (enabled) White else Gray3
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SoptButtonPreview() {
    PlaySoptTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            SoptButton(
                text = "로그인",
                enabled = true,
                onClick = {}
            )
            SoptButton(
                text = "로그인",
                enabled = false,
                onClick = {}
            )
        }
    }
}