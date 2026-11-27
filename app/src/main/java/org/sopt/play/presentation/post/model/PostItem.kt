package org.sopt.play.presentation.post.model

import org.sopt.play.data.remote.dto.PostResponse

data class PostItem(
    val id: Long,
    val title: String,
    val content: String,
    val author: String,
    val createdAt: String,
)

fun PostResponse.toPostItem(): PostItem = PostItem(
    id = id,
    title = title,
    content = content,
    author = author,
    createdAt = createdAt.toDisplayDate(),
)

// "2026-11-25T13:42:26.174" → "2026.11.25"
private fun String.toDisplayDate(): String = take(10).replace("-", ".")
