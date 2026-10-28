package org.sopt.play.presentation.home.model

import androidx.annotation.DrawableRes

data class Banner(
    val id: Int,
    @DrawableRes val imageRes: Int,
    val description: String,
)

