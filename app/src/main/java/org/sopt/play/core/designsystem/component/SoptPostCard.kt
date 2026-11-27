package org.sopt.play.core.designsystem.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.core.designsystem.theme.Gray1
import org.sopt.play.core.designsystem.theme.Gray3
import org.sopt.play.core.designsystem.theme.Gray5
import org.sopt.play.core.designsystem.theme.PlaySoptTheme
import org.sopt.play.core.designsystem.theme.SoptTypography

// 공통 컴포넌트라서 게시글 모델 대신 필요한 값만 받아요 (3주차 리뷰 반영)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SoptPostCard(
    author: String,
    createdAt: String,
    title: String,
    content: String,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(Gray1)
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick,
            )
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = author,
                style = SoptTypography.m12,
                color = Gray3,
            )
            Box(
                modifier = Modifier
                    .width(1.dp)
                    .height(12.dp)
                    .background(Gray3),
            )
            Text(
                text = createdAt,
                style = SoptTypography.m12,
                color = Gray3,
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = title,
                style = SoptTypography.m16,
                color = Gray5,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = content,
                style = SoptTypography.m14,
                color = Gray5,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SoptPostCardPreview() {
    PlaySoptTheme {
        SoptPostCard(
            author = "김솝트",
            createdAt = "2026.11.25",
            title = "제목",
            content = "본문입니다.",
            onClick = {},
            onLongClick = {},
        )
    }
}
