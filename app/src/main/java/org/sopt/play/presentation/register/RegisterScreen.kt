package org.sopt.play.presentation.register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.sopt.play.core.designsystem.component.SoptButton
import org.sopt.play.core.designsystem.component.SoptTextField
import org.sopt.play.core.designsystem.theme.Black
import org.sopt.play.core.designsystem.theme.PlaySoptTheme
import org.sopt.play.core.designsystem.theme.SoptTypography

@Composable
fun RegisterRoute(
    onRegisterSuccess: (String, String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RegisterViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is RegisterSideEffect.NavigateToLogin -> onRegisterSuccess(
                    sideEffect.email,
                    sideEffect.password,
                )
            }
        }
    }

    RegisterScreen(
        uiState = uiState,
        onNameChange = viewModel::onNameChange,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onPasswordCheckChange = viewModel::onPasswordCheckChange,
        onRegisterClick = viewModel::onRegisterClick,
        modifier = modifier,
    )
}

@Composable
fun RegisterScreen(
    uiState: RegisterUiState,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onPasswordCheckChange: (String) -> Unit,
    onRegisterClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .imePadding()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        Text(
            text = "이메일로 회원가입",
            style = SoptTypography.b28,
            color = Black
        )

        Spacer(modifier = Modifier.height(40.dp))

        SoptTextField(
            label = "이름",
            value = uiState.name,
            onValueChange = onNameChange,
            placeholder = "홍길동"
        )

        Spacer(modifier = Modifier.height(32.dp))

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

        Spacer(modifier = Modifier.height(32.dp))

        SoptTextField(
            label = "비밀번호 확인",
            value = uiState.passwordCheck,
            onValueChange = onPasswordCheckChange,
            placeholder = "6자 이상의 비밀번호",
            isPassword = true,
            isError = uiState.isPasswordCheckError,
            errorMessage = "비밀번호와 동일하게 입력해주세요."
        )

        Spacer(modifier = Modifier.height(40.dp))

        SoptButton(
            text = "회원가입",
            enabled = uiState.isButtonEnabled,
            onClick = onRegisterClick,
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
)
@Composable
private fun RegisterScreenPreview() {
    PlaySoptTheme {
        RegisterScreen(
            uiState = RegisterUiState(),
            onNameChange = {},
            onEmailChange = {},
            onPasswordChange = {},
            onPasswordCheckChange = {},
            onRegisterClick = {},
        )
    }
}
