package org.sopt.play.presentation.home

import org.sopt.play.presentation.home.model.Banner
import org.sopt.play.presentation.home.model.HomeCategory
import org.sopt.play.presentation.home.model.HomeMenu
import org.sopt.play.core.model.Product

data class HomeUiState(
    val userName: String = "김솝트",
    val banners: List<Banner> = emptyList(),
    val products: List<Product> = emptyList(),
    val selectedMenu: HomeMenu = HomeMenu.HOME,
    val selectedCategory: HomeCategory = HomeCategory.ALL,
)
