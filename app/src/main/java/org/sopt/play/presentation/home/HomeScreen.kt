package org.sopt.play.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.sopt.play.core.designsystem.component.SoptProductCard
import org.sopt.play.core.designsystem.theme.PlaySoptTheme
import org.sopt.play.core.designsystem.theme.White
import org.sopt.play.presentation.home.component.HomeBanner
import org.sopt.play.presentation.home.component.HomeMenuTab
import org.sopt.play.presentation.home.component.HomeRecommendHeader
import org.sopt.play.presentation.home.component.HomeTopBar
import org.sopt.play.presentation.home.dummy.HomeDummyData
import org.sopt.play.presentation.home.model.HomeCategory
import org.sopt.play.presentation.home.model.HomeMenu

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreen(
        uiState = uiState,
        onMenuClick = viewModel::onMenuClick,
        onCategoryClick = viewModel::onCategoryClick,
        onLikeClick = viewModel::onLikeClick,
        modifier = modifier,
    )
}
@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onMenuClick: (HomeMenu) -> Unit,
    onCategoryClick: (HomeCategory) -> Unit,
    onLikeClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(White),
    ) {
        HomeTopBar()

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(bottom = 96.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            item(
                span = { GridItemSpan(maxLineSpan) },
            ) {
                HomeMenuTab(
                    selectedMenu = uiState.selectedMenu,
                    onMenuClick = onMenuClick,
                )
            }

            item(
                span = { GridItemSpan(maxLineSpan) },
            ) {
                HomeBanner(
                    banners = uiState.banners,
                    modifier = Modifier.padding(vertical = 8.dp),
                )
            }

            item(
                span = { GridItemSpan(maxLineSpan) },
            ) {
                HomeRecommendHeader(
                    userName = uiState.userName,
                    selectedCategory = uiState.selectedCategory,
                    onCategoryClick = onCategoryClick,
                    modifier = Modifier.padding(
                        top = 20.dp,
                        bottom = 20.dp,
                    ),
                )
            }

            items(
                items = uiState.products,
                key = { it.id },
            ) { product ->
                SoptProductCard(
                    product = product,
                    onLikeClick = { onLikeClick(product.id) },
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
)
@Composable
private fun HomeScreenPreview() {
    PlaySoptTheme {
        HomeScreen(
            uiState = HomeUiState(
                banners = HomeDummyData.banners,
                products = HomeDummyData.products,
            ),
            onMenuClick = {},
            onCategoryClick = {},
            onLikeClick = {},
        )
    }
}
