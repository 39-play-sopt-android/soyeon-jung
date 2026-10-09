package org.sopt.play.presentation.login

import android.util.Patterns
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import org.sopt.play.core.designsystem.theme.Gray3
import org.sopt.play.core.designsystem.theme.PlaySoptTheme
import org.sopt.play.core.designsystem.theme.SoptTypography

@Composable
fun LoginScreen(
    onLoginClick: (String, String) -> Unit,
    onRegisterClick: () ->Unit,
    modifier: Modifier = Modifier
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val isEmailError = email.isNotEmpty() && !Patterns.EMAIL_ADDRESS.matcher(email).matches()
    val isPasswordError = password.isNotEmpty() && password.length < 6
    val isButtonEnabled =
        email.isNotEmpty() && password.isNotEmpty() && !isEmailError && !isPasswordError

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(text = "이메일로 로그인하기", style = SoptTypography.b28, color = Black)
        Spacer(modifier = Modifier.height(32.dp))

        SoptTextField(
            label = "이메일 주소", value = email, onValueChange = { email = it },
            "abc@email.com",
            isError = isEmailError, errorMessage = "올바른 이메일을 입력해주세요."
        )
        Spacer(modifier = Modifier.height(20.dp))
        SoptTextField(
            label = "비밀번호", value = password, onValueChange = { password = it },
            placeholder = "6자 이상의 비밀번호", isPassword = true,
            isError = isPasswordError, errorMessage = "비밀번호는 6자 이상 입니다."
        )
        Spacer(modifier = Modifier.height(40.dp))
        SoptButton(
            text = "로그인",
            enabled = isButtonEnabled,
            onClick = { onLoginClick(email, password) })
        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "아직 계정이 없으신가요?", style = SoptTypography.m14, color = Gray3)
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "회원가입하기",
                style = SoptTypography.sb14,
                color = Black,
                modifier = Modifier.clickable { onRegisterClick() }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable private fun LoginScreenPreview() {
    PlaySoptTheme() {
        LoginScreen(
            onLoginClick = { _, _ -> },
            onRegisterClick = {}
        )
    }
}
