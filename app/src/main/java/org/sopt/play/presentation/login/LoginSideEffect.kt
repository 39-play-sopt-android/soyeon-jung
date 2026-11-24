package org.sopt.play.presentation.login

sealed interface LoginSideEffect {
    data object NavigateToHome : LoginSideEffect

    data object ShowLoginFailToast : LoginSideEffect
}
