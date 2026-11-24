package org.sopt.play.core.model

import androidx.annotation.DrawableRes

data class Product(
    val id: Int,
    @DrawableRes val imageRes: Int,
    val storeName: String,
    val productName: String,
    val discountRate: Int,
    val price: Int,
    val isLiked: Boolean,
)
