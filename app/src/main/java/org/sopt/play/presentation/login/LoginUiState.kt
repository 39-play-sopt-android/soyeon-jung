package org.sopt.play.presentation.login

import org.sopt.play.core.util.AuthValidator

data class LoginUiState(
    val email: String = "",
    val password: String = "",
) {
    val isEmailError: Boolean
        get() = AuthValidator.isEmailError(email)

    val isPasswordError: Boolean
        get() = AuthValidator.isPasswordError(password)

    val isButtonEnabled: Boolean
        get() = email.isNotEmpty() && password.isNotEmpty() && !isEmailError && !isPasswordError
}
