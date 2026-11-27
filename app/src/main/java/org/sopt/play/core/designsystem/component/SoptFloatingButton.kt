package org.sopt.play.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import org.sopt.play.R
import org.sopt.play.core.designsystem.theme.PlaySoptTheme
import org.sopt.play.core.designsystem.theme.Red
import org.sopt.play.core.designsystem.theme.White

@Composable
fun SoptFloatingButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(60.dp)
            .dropShadow(
                shape = CircleShape,
                shadow = androidx.compose.ui.graphics.shadow.Shadow(
                    radius = 12.dp,
                    color = Color.Black.copy(alpha = 0.15f),
                    offset = DpOffset(x = 0.dp, y = 4.dp),
                ),
            )
            .clip(CircleShape)
            .background(Red)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_plus),
            contentDescription = "게시글 작성",
            tint = White,
            modifier = Modifier.size(28.dp),
        )
    }
}

@Preview
@Composable
private fun SoptFloatingButtonPreview() {
    PlaySoptTheme {
        SoptFloatingButton(onClick = {})
    }
}
