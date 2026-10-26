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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.core.designsystem.component.SoptProductCard
import org.sopt.play.core.designsystem.theme.PlaySoptTheme
import org.sopt.play.core.designsystem.theme.White
import org.sopt.play.presentation.home.component.HomeBanner
import org.sopt.play.presentation.home.component.HomeMenuTab
import org.sopt.play.presentation.home.component.HomeRecommendHeader
import org.sopt.play.presentation.home.component.HomeTopBar
import org.sopt.play.presentation.home.model.Banner
import org.sopt.play.data.dummy.HomeDummyData
import org.sopt.play.presentation.home.model.HomeCategory
import org.sopt.play.presentation.home.model.HomeMenu
import org.sopt.play.presentation.home.model.Product

@Composable
fun HomeScreen(
    userName: String,
    banners: List<Banner>,
    products: List<Product>,
    modifier: Modifier = Modifier,
) {
    var selectedMenu by rememberSaveable { mutableStateOf(HomeMenu.HOME) }
    var selectedCategory by rememberSaveable { mutableStateOf(HomeCategory.ALL) }

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
                    selectedMenu = selectedMenu,
                    onMenuClick = { selectedMenu = it },
                )
            }

            item(
                span = { GridItemSpan(maxLineSpan) },
            ) {
                HomeBanner(
                    banners = banners,
                    modifier = Modifier.padding(vertical = 8.dp),
                )
            }

            item(
                span = { GridItemSpan(maxLineSpan) },
            ) {
                HomeRecommendHeader(
                    userName = userName,
                    selectedCategory = selectedCategory,
                    onCategoryClick = { selectedCategory = it },
                    modifier = Modifier.padding(
                        top = 20.dp,
                        bottom = 20.dp,
                    ),
                )
            }

            items(
                items = products,
                key = { it.id },
            ) { product ->
                SoptProductCard(
                    product = product,
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
            userName = "김솝트",
            banners = HomeDummyData.banners,
            products = HomeDummyData.products,
        )
    }
}
