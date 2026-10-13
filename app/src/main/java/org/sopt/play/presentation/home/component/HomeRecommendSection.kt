package org.sopt.play.presentation.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.sopt.play.core.designsystem.component.SoptChip
import org.sopt.play.core.designsystem.theme.Black
import org.sopt.play.core.designsystem.theme.SoptTypography

@Composable
fun HomeRecommendSection(
    userName: String,
    categories: List<String>,
    selectedCategory: String,
    onCategoryClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        Text(
            text = "${userName}님을 위한 추천 아이템",
            style = SoptTypography.sb14,
            color = Black,
            modifier = Modifier.padding(horizontal = 16.dp),
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            items(
                items = categories,
                key = { it },
            ) { category ->
                SoptChip(
                    text = category,
                    isSelected = category == selectedCategory,
                    onClick = { onCategoryClick(category)
                              },
                )
            }
        }
    }
}
