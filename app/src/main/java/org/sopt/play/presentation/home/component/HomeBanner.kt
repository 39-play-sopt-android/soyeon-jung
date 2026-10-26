package org.sopt.play.presentation.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.core.designsystem.theme.PlaySoptTheme
import org.sopt.play.presentation.home.model.Banner
import org.sopt.play.presentation.home.model.HomeDummyData

@Composable
fun HomeBanner(
    banners: List<Banner>,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(
            items = banners,
            key = { it.id },
        ) { banner ->
            Image(
                painter = painterResource(id = banner.imageRes),
                contentDescription = banner.description,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillParentMaxWidth()
                    .aspectRatio(328f / 195f),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeBannerPreview() {
    PlaySoptTheme {
        HomeBanner(
            banners = HomeDummyData.banners,
        )
    }
}
