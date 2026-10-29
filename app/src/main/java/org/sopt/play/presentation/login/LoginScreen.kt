package org.sopt.play.presentation.login

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.sopt.play.core.designsystem.component.SoptButton
import org.sopt.play.core.designsystem.component.SoptTextField
import org.sopt.play.core.designsystem.theme.Black
import org.sopt.play.core.designsystem.theme.Gray3
import org.sopt.play.core.designsystem.theme.Gray6
import org.sopt.play.core.designsystem.theme.PlaySoptTheme
import org.sopt.play.core.designsystem.theme.SoptTypography

@Composable
fun LoginRoute(
    onLoginSuccess: () -> Unit,
    onRegisterClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = viewModel(),
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                LoginSideEffect.NavigateToHome -> onLoginSuccess()

            LoginSideEffect.ShowLoginFailToast -> Toast.makeText(
            context,
            "이메일 또는 비밀번호가 올바르지 않아요.",
            Toast.LENGTH_SHORT,
            ).show()
        }
    }
}

    LoginScreen(
        uiState = uiState,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onLoginClick = viewModel::onLoginClick,
        onRegisterClick = onRegisterClick,
        modifier = modifier,
    )
}

@Composable
fun LoginScreen(
    uiState: LoginUiState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .imePadding()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        Text(
            text = "이메일로 로그인하기",
            style = SoptTypography.b28,
            color = Black
        )

        Spacer(modifier = Modifier.height(40.dp))

        SoptTextField(
            label = "이메일 주소",
            value = uiState.email,
            onValueChange = onEmailChange,
            placeholder = "abc@email.com",
            isError = uiState.isEmailError,
            errorMessage = "올바른 이메일을 입력해주세요."
        )

        Spacer(modifier = Modifier.height(32.dp))

        SoptTextField(
            label = "비밀번호",
            value = uiState.password,
            onValueChange = onPasswordChange,
            placeholder = "6자 이상의 비밀번호",
            isPassword = true,
            isError = uiState.isPasswordError,
            errorMessage = "비밀번호는 6자 이상 입력해주세요."
        )

        Spacer(modifier = Modifier.height(40.dp))

        SoptButton(
            text = "로그인",
            enabled = uiState.isButtonEnabled,
            onClick = onLoginClick,
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "아직 계정이 없으신가요?",
                style = SoptTypography.m14,
                color = Gray3
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = "회원가입하기",
                style = SoptTypography.m14,
                color = Gray6,
                modifier = Modifier.clickable { onRegisterClick() }
            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
)
@Composable
private fun LoginScreenPreview() {
    PlaySoptTheme {
        LoginScreen(
            uiState = LoginUiState(),
            onEmailChange = {},
            onPasswordChange = {},
            onLoginClick = {},
            onRegisterClick = {},
        )
    }
}
