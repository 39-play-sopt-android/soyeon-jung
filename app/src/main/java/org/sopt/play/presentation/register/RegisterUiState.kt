package org.sopt.play.presentation.register

import android.util.Patterns

data class RegisterUiState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val passwordCheck: String = "",
) {
    val isEmailError: Boolean
        get() = email.isNotEmpty() && !Patterns.EMAIL_ADDRESS.matcher(email).matches()

    val isPasswordError: Boolean
        get() = password.isNotEmpty() && password.length < 6

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
