package org.sopt.play.presentation.post

import org.sopt.play.presentation.post.model.PostItem

data class PostUiState(
    val posts: List<PostItem> = emptyList(),
    val keyword: String = "",
    val isLoading: Boolean = false,
) {
    // 불러오는 중이 아닌데 목록이 비어 있으면 Empty UI
    val isEmpty: Boolean
        get() = posts.isEmpty() && !isLoading
}
