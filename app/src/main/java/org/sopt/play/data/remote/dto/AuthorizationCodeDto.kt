package org.sopt.play.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthorizationCodeRequest(
    @SerialName("part")
    val part: String,
)

@Serializable
data class AuthorizationCodeResponse(
    @SerialName("authorizationCode")
    val authorizationCode: String,
)
