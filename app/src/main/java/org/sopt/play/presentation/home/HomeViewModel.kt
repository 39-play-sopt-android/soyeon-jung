package org.sopt.play.presentation.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.sopt.play.presentation.home.dummy.HomeDummyData
import org.sopt.play.presentation.home.model.HomeCategory
import org.sopt.play.presentation.home.model.HomeMenu

class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(
        HomeUiState(
            banners = HomeDummyData.banners,
            products = HomeDummyData.products,
        ),
    )
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun onMenuClick(menu: HomeMenu) {
        _uiState.update { it.copy(selectedMenu = menu) }
    }

    fun onCategoryClick(category: HomeCategory) {
        _uiState.update { it.copy(selectedCategory = category) }
    }

    fun onLikeClick(productId: Int) {
        _uiState.update { state ->
            state.copy(
                products = state.products.map { product ->
                    if (product.id == productId) {
                        product.copy(isLiked = !product.isLiked)
                    } else {
                        product
                    }
                },
            )
        }
    }
}
