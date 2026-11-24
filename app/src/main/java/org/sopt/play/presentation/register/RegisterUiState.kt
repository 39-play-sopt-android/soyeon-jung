package org.sopt.play.presentation.register

import org.sopt.play.core.util.AuthValidator

data class RegisterUiState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val passwordCheck: String = "",
) {
    val isEmailError: Boolean
        get() = AuthValidator.isEmailError(email)

    val isPasswordError: Boolean
        get() = AuthValidator.isPasswordError(password)

    val isPasswordCheckError: Boolean
        get() = passwordCheck.isNotEmpty() && passwordCheck != password

    val isButtonEnabled: Boolean
        get() = name.isNotBlank() &&
                email.isNotEmpty() &&
                password.isNotEmpty() &&
                passwordCheck.isNotEmpty() &&
                !isEmailError &&
                !isPasswordError &&
                !isPasswordCheckError
}
