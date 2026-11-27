package org.sopt.play.presentation.post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.sopt.play.data.remote.ServicePool
import org.sopt.play.presentation.post.model.toPostItem

class PostViewModel : ViewModel() {
    private val postService = ServicePool.postService

    private val _uiState = MutableStateFlow(PostUiState())
    val uiState: StateFlow<PostUiState> = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<PostSideEffect>()
    val sideEffect: SharedFlow<PostSideEffect> = _sideEffect.asSharedFlow()

    // 게시글 페이지 첫 진입 시 목록 조회
    init {
        getPosts()
    }

    fun onKeywordChange(keyword: String) {
        _uiState.update { it.copy(keyword = keyword) }
    }

    fun onSearch() {
        val keyword = _uiState.value.keyword.trim()
        // 서버 규칙: 검색어는 2자 이상 (비어 있으면 전체 목록)
        if (keyword.length == 1) {
            viewModelScope.launch { showToast("검색어는 2자 이상 입력해주세요.") }
            return
        }
        getPosts()
    }

    private fun getPosts() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            // 비어 있으면 null → 쿼리 없이 전체 목록
            val keyword = _uiState.value.keyword.trim().ifBlank { null }

            runCatching {
                postService.getPosts(keyword = keyword)
            }.onSuccess { response ->
                val posts = response.data?.posts.orEmpty().map { it.toPostItem() }
                _uiState.update { it.copy(posts = posts, isLoading = false) }
            }.onFailure {
                _uiState.update { it.copy(isLoading = false) }
                showToast("게시글을 불러오지 못했어요.")
            }
        }
    }

    private suspend fun showToast(message: String) {
        _sideEffect.emit(PostSideEffect.ShowToast(message))
    }
}
