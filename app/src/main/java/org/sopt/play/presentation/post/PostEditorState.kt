package org.sopt.play.presentation.post

// 바텀시트 하나로 작성/수정을 같이 처리
data class PostEditorState(
    val postId: Long? = null,
    val title: String = "",
    val content: String = "",
    val initialTitle: String = "",
    val initialContent: String = "",
) {
    // postId가 없으면 작성, 있으면 수정
    val isEditMode: Boolean
        get() = postId != null

    private val isFilled: Boolean
        get() = title.isNotBlank() && content.isNotBlank()

    // 초기값과 하나라도 다르면 true (A → B → A로 돌아오면 다시 false)
    private val isChanged: Boolean
        get() = title != initialTitle || content != initialContent

    val isButtonEnabled: Boolean
        get() = if (isEditMode) isFilled && isChanged else isFilled

    companion object {
        const val MAX_TITLE_LENGTH = 20
        const val MAX_CONTENT_LENGTH = 1000
    }
}
