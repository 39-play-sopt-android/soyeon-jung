package org.sopt.play.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
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
import org.sopt.play.core.designsystem.component.ProductCard
import org.sopt.play.core.designsystem.theme.PlaySoptTheme
import org.sopt.play.core.designsystem.theme.White
import org.sopt.play.presentation.home.component.HomeBanner
import org.sopt.play.presentation.home.component.HomeMenuTab
import org.sopt.play.presentation.home.component.HomeRecommendSection
import org.sopt.play.presentation.home.component.HomeTopBar
import org.sopt.play.presentation.home.model.HomeDummyData

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
) {
    var selectedMenu by rememberSaveable { mutableStateOf("홈") }
    var selectedCategory by rememberSaveable { mutableStateOf("전체") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(White),
    ) {
        HomeTopBar()

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(bottom = 40.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            item(
                span = { GridItemSpan(maxLineSpan) },
            ) {
                HomeMenuTab(
                    menus = HomeDummyData.menus,
                    selectedMenu = selectedMenu,
                    onMenuClick = { selectedMenu = it },
                )
            }

            item(
                span = { GridItemSpan(maxLineSpan) },
            ) {
                HomeBanner(
                    banners = HomeDummyData.banners,
                )
            }

            item(
                span = { GridItemSpan(maxLineSpan) },
            ) {
                HomeRecommendSection(
                    userName = "김솝트",
                    categories = HomeDummyData.categories,
                    selectedCategory = selectedCategory,
                    onCategoryClick = { selectedCategory = it },
                )
            }

            items(
                items = HomeDummyData.products,
                key = { it.id },
            ) { product ->
                ProductCard(
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
        HomeScreen()
    }
}
