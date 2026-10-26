package org.sopt.play.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data class Login(
        val email: String = "",
        val password: String = "",
    ) : Route

    @Serializable
    data object Register : Route

    @Serializable
    data object Home : Route
}
