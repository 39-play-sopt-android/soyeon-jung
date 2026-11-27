package org.sopt.play.presentation.post.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import org.sopt.play.core.designsystem.theme.Black
import org.sopt.play.core.designsystem.theme.Gray1
import org.sopt.play.core.designsystem.theme.Gray5
import org.sopt.play.core.designsystem.theme.Gray6
import org.sopt.play.core.designsystem.theme.PlaySoptTheme
import org.sopt.play.core.designsystem.theme.SoptTypography
import org.sopt.play.core.designsystem.theme.White

// Dialog는 바깥을 누르면 onDismissRequest가 불려서 자동으로 닫혀요
@Composable
fun PostDeleteDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    Dialog(onDismissRequest = onDismiss) {
        PostDeleteDialogContent(
            onConfirm = onConfirm,
            onDismiss = onDismiss,
        )
    }
}

@Composable
private fun PostDeleteDialogContent(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    Column(
        modifier = Modifier
            .width(288.dp)
            .background(White, RoundedCornerShape(12.dp))
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        Text(
            text = "게시글을 삭제할까요?",
            style = SoptTypography.m18,
            color = Gray6,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            DialogButton(
                text = "취소",
                containerColor = Gray1,
                contentColor = Gray5,
                onClick = onDismiss,
                modifier = Modifier.weight(1f),
            )
            DialogButton(
                text = "삭제",
                containerColor = Black,
                contentColor = Gray1,
                onClick = onConfirm,
                modifier = Modifier.weight(1f),
            )
        }
    }
}

@Composable
private fun DialogButton(
    text: String,
    containerColor: Color,
    contentColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(containerColor)
            .clickable(onClick = onClick)
            .padding(16.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            style = SoptTypography.sb14,
            color = contentColor,
        )
    }
}

@Preview
@Composable
private fun PostDeleteDialogPreview() {
    PlaySoptTheme {
        PostDeleteDialogContent(
            onConfirm = {},
            onDismiss = {},
        )
    }
}
