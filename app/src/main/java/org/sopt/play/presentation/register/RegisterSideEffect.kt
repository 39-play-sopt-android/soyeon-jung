package org.sopt.play.presentation.register

sealed interface RegisterSideEffect {
    data class NavigateToLogin(
        val name: String,
        val email: String,
        val password: String,
    ) : RegisterSideEffect
}
