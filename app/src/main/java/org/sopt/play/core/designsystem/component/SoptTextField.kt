package org.sopt.play.core.designsystem.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.core.designsystem.theme.Black
import org.sopt.play.core.designsystem.theme.Gray2
import org.sopt.play.core.designsystem.theme.Gray3
import org.sopt.play.core.designsystem.theme.Gray5
import org.sopt.play.core.designsystem.theme.PlaySoptTheme
import org.sopt.play.core.designsystem.theme.Red
import org.sopt.play.core.designsystem.theme.SoptTypography

@Composable
fun SoptTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    errorMessage: String = "",
    isPassword: Boolean = false
) {
    var isFocused by remember { mutableStateOf(false)}

    val borderColor = when {
        isError -> Red
        isFocused -> Gray5
        else -> Gray2
    }

    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = label,
            style = SoptTypography.m14,
            color = Black,
            modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
        )
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            textStyle = SoptTypography.m18.copy(color = Black),
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, borderColor, RoundedCornerShape(12.dp))
                .padding(16.dp)
                .onFocusChanged { isFocused = it.isFocused },
            decorationBox = { innerTextField ->
                if (value.isEmpty()) {
                    Text(text = placeholder, style = SoptTypography.m18, color = Gray3)
                }
                innerTextField()
            }
        )
        if (isError) {
            Text(
                text = errorMessage,
                style = SoptTypography.m14,
                color = Red,
                modifier = Modifier.padding(start = 8.dp, top = 6.dp)
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
private fun SoptTextFieldDefaultPreview() {
    PlaySoptTheme {
        SoptTextField(
            label = "아이디",
            value = "",
            onValueChange = {},
            placeholder = "아이디를 입력해주세요"
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SoptTextFieldErrorPreview() {
    PlaySoptTheme {
        SoptTextField(
            label = "아이디",
            value = "abc",
            onValueChange = {},
            placeholder = "아이디를 입력해주세요",
            isError = true,
            errorMessage = "아이디는 6~10자로 입력해주세요"
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SoptTextFieldPasswordPreview() {
    PlaySoptTheme {
        SoptTextField(
            label = "비밀번호",
            value = "password123",
            onValueChange = {},
            placeholder = "비밀번호를 입력해주세요",
            isPassword = true
        )
    }
}