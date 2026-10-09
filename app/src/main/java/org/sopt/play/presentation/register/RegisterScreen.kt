package org.sopt.play.presentation.register

import android.util.Patterns
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.core.designsystem.component.SoptButton
import org.sopt.play.core.designsystem.component.SoptTextField
import org.sopt.play.core.designsystem.theme.Black
import org.sopt.play.core.designsystem.theme.PlaySoptTheme
import org.sopt.play.core.designsystem.theme.SoptTypography

@Composable
fun RegisterScreen(
    onRegisterClick: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordCheck by remember { mutableStateOf("") }

    val isEmailError = email.isNotEmpty() && !Patterns.EMAIL_ADDRESS.matcher(email).matches()
    val isPasswordError = password.isNotEmpty() && password.length < 6
    val isPasswordCheckError = passwordCheck.isNotEmpty() && passwordCheck != password

    val isButtonEnabled =
        email.isNotEmpty() && password.isNotEmpty() && passwordCheck.isNotEmpty() && !isEmailError && !isPasswordError && !isPasswordCheckError

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(text = "이메일로 회원가입", style = SoptTypography.b28, color = Black)
        Spacer(modifier = Modifier.height(32.dp))

        SoptTextField(
            label = "이름",
            value = name,
            onValueChange = { name = it },
            placeholder = "홍길동"
        )
        Spacer(modifier = Modifier.height(20.dp))
        SoptTextField(
            label = "이메일 주소", value = email, onValueChange = { email = it },
            placeholder = "abc@email.com",
            isError = isEmailError, errorMessage = "올바른 이메일을 입력해주세요."
        )
        Spacer(modifier = Modifier.height(20.dp))
        SoptTextField(
            label = "비밀번호", value = password, onValueChange = { password = it },
            placeholder = "6자 이상의 비밀번호", isPassword = true,
            isError = isPasswordError, errorMessage = "비밀번호는 6자 이상 입력해주세요."
        )
        Spacer(modifier = Modifier.height(20.dp))
        SoptTextField(
            label = "비밀번호 확인", value = passwordCheck, onValueChange = { passwordCheck = it },
            placeholder = "6자 이상의 비밀번호", isPassword = true,
            isError = isPasswordCheckError, errorMessage = "비밀번호와 동일하게 입력해주세요."
        )
        Spacer(modifier = Modifier.height(40.dp))
        SoptButton(
            text = "회원가입",
            enabled = isButtonEnabled,
            onClick = { onRegisterClick(email, password) })
    }
}
@Preview(showBackground = true)
@Composable
private fun RegisterScreenPreview() {
    PlaySoptTheme {
        RegisterScreen(
            onRegisterClick = { _, _ -> }
        )
    }
}