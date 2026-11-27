package org.sopt.play.presentation.post

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.sopt.play.core.designsystem.component.SoptFloatingButton
import org.sopt.play.core.designsystem.component.SoptPostCard
import org.sopt.play.core.designsystem.component.SoptSearchTextField
import org.sopt.play.core.designsystem.theme.Gray3
import org.sopt.play.core.designsystem.theme.PlaySoptTheme
import org.sopt.play.core.designsystem.theme.SoptTypography
import org.sopt.play.core.designsystem.theme.White
import org.sopt.play.presentation.post.model.PostItem

@Composable
fun PostRoute(
    modifier: Modifier = Modifier,
    viewModel: PostViewModel = viewModel(),
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is PostSideEffect.ShowToast -> {
                    Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    PostScreen(
        uiState = uiState,
        onKeywordChange = viewModel::onKeywordChange,
        onSearch = viewModel::onSearch,
        onPostClick = {},
        onPostLongClick = {},
        onFabClick = {},
        modifier = modifier,
    )
}

@Composable
fun PostScreen(
    uiState: PostUiState,
    onKeywordChange: (String) -> Unit,
    onSearch: () -> Unit,
    onPostClick: (PostItem) -> Unit,
    onPostLongClick: (Long) -> Unit,
    onFabClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(White),
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            SoptSearchTextField(
                value = uiState.keyword,
                onValueChange = onKeywordChange,
                onSearch = onSearch,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
            )

            if (!uiState.isEmpty) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 100.dp,
                    ),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    items(
                        items = uiState.posts,
                        key = { it.id },
                    ) { post ->
                        SoptPostCard(
                            author = post.author,
                            createdAt = post.createdAt,
                            title = post.title,
                            content = post.content,
                            onClick = { onPostClick(post) },
                            onLongClick = { onPostLongClick(post.id) },
                        )
                    }
                }
            }
        }

        // 피그마처럼 검색창 아래가 아니라 화면 정가운데에 배치
        if (uiState.isEmpty) {
            Text(
                text = "검색 결과가 없습니다.",
                style = SoptTypography.m18,
                color = Gray3,
                modifier = Modifier.align(Alignment.Center),
            )
        }

        SoptFloatingButton(
            onClick = onFabClick,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 16.dp, bottom = 20.dp),
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
)
@Composable
private fun PostScreenPreview() {
    PlaySoptTheme {
        PostScreen(
            uiState = PostUiState(
                posts = listOf(
                    PostItem(1, "제목", "본문입니다.", "김솝트", "2026.11.25"),
                    PostItem(2, "솝트 세미나 후기", "본문이 길어지면 최대 세 줄까지 보이고 나머지는 말줄임표로 처리돼요. ".repeat(3), "이솝트", "2026.11.24"),
                ),
            ),
            onKeywordChange = {},
            onSearch = {},
            onPostClick = {},
            onPostLongClick = {},
            onFabClick = {},
        )
    }
}
