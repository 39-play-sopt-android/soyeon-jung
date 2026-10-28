package org.sopt.play.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.core.designsystem.theme.Gray2
import org.sopt.play.core.designsystem.theme.Gray5
import org.sopt.play.core.designsystem.theme.PlaySoptTheme
import org.sopt.play.core.designsystem.theme.SoptTypography
import org.sopt.play.core.designsystem.theme.White

@Composable
fun SoptChip(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        style = SoptTypography.m14,
        color = if (isSelected) White else Gray5,
        modifier = modifier
            .clip(RoundedCornerShape(6.dp))
            .background(if (isSelected) Gray5 else White)
            .border(
                width = 1.dp,
                color = if (isSelected) Gray5 else Gray2,
                shape = RoundedCornerShape(6.dp),
            )
            .clickable(onClick = onClick)
            .padding(
                horizontal = 12.dp,
                vertical = 8.dp,
            ),
    )
}

@Preview(showBackground = true)
@Composable
private fun SoptChipPreview() {
    PlaySoptTheme {
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            SoptChip(
                text = "전체",
                isSelected = true,
                onClick = {},
            )

            SoptChip(
                text = "상의",
                isSelected = false,
                onClick = {},
            )
        }
    }
}
