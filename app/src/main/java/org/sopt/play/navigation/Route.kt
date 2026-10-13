package org.sopt.play.navigation

import kotlinx.serialization.Serializable

@Serializable
data class Login(
    val email: String = "",
    val password: String = "",
)

@Serializable
data object Register

@Serializable
data object Home
