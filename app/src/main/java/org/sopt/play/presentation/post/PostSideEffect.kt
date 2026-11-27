package org.sopt.play.presentation.post

sealed interface PostSideEffect {
    data class ShowToast(val message: String) : PostSideEffect
}
