package org.sopt.play.presentation.home.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.sopt.play.core.designsystem.component.SoptMenuItem

@Composable
fun HomeMenuTab(
    menus: List<String>,
    selectedMenu: String,
    onMenuClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 4.dp),
    ) {
        items(
            items = menus,
            key = { it },
        ) { menu ->
            SoptMenuItem(
                text = menu,
                isSelected = menu == selectedMenu,
                onClick = { onMenuClick(menu) },
            )
        }
    }
}
