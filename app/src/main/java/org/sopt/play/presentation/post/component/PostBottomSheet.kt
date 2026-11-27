package org.sopt.play.presentation.post.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import org.sopt.play.core.designsystem.component.SoptButton
import org.sopt.play.core.designsystem.component.SoptTextField
import org.sopt.play.core.designsystem.theme.Gray3
import org.sopt.play.core.designsystem.theme.White
import org.sopt.play.presentation.post.PostEditorState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostBottomSheet(
    editor: PostEditorState,
    onTitleChange: (String) -> Unit,
    onContentChange: (String) -> Unit,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        containerColor = White,
        dragHandle = {
            Box(
                modifier = Modifier
                    .padding(top = 12.dp, bottom = 20.dp)
                    .size(width = 36.dp, height = 4.dp)
                    .clip(CircleShape)
                    .background(Gray3),
            )
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp),
        ) {
            SoptTextField(
                label = "제목",
                value = editor.title,
                onValueChange = onTitleChange,
                placeholder = "제목을 입력하세요.",
            )

            Spacer(modifier = Modifier.height(12.dp))

            SoptTextField(
                label = "본문",
                value = editor.content,
                onValueChange = onContentChange,
                placeholder = "본문을 입력하세요.",
            )

            Spacer(modifier = Modifier.height(20.dp))

            SoptButton(
                text = "완료",
                enabled = editor.isButtonEnabled,
                onClick = onConfirm,
            )
        }
    }
}
