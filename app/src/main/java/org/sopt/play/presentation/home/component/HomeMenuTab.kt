package org.sopt.play.presentation.home.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.play.core.designsystem.component.SoptMenuItem
import org.sopt.play.core.designsystem.theme.PlaySoptTheme
import org.sopt.play.presentation.home.model.HomeMenu

@Composable
fun HomeMenuTab(
    selectedMenu: HomeMenu,
    onMenuClick: (HomeMenu) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
    ) {
        items(
            items = HomeMenu.entries,
            key = { it.name },
        ) { menu ->
            SoptMenuItem(
                text = menu.title,
                isSelected = menu == selectedMenu,
                onClick = { onMenuClick(menu) },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeMenuTabPreview() {
    PlaySoptTheme {
        HomeMenuTab(
            selectedMenu = HomeMenu.HOME,
            onMenuClick = {},
        )
    }
}
