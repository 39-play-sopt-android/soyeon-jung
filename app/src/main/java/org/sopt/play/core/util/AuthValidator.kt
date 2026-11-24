package org.sopt.play.core.util

import android.util.Patterns

object AuthValidator {
    private const val MIN_PASSWORD_LENGTH = 6

    fun isEmailError(email: String): Boolean =
        email.isNotEmpty() && !Patterns.EMAIL_ADDRESS.matcher(email).matches()

    fun isPasswordError(password: String): Boolean =
        password.isNotEmpty() && password.length < MIN_PASSWORD_LENGTH
}
