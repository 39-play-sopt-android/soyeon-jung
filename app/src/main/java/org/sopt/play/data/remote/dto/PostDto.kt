package org.sopt.play.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// GET /api/v1/posts 응답의 data
@Serializable
data class PostSliceResponse(
    @SerialName("posts")
    val posts: List<PostResponse>,
    @SerialName("nextCursor")
    val nextCursor: Long? = null,
    @SerialName("hasNext")
    val hasNext: Boolean,
)

// 게시글 하나 (목록, 작성, 수정 응답 공통)
@Serializable
data class PostResponse(
    @SerialName("id")
    val id: Long,
    @SerialName("title")
    val title: String,
    @SerialName("content")
    val content: String,
    @SerialName("author")
    val author: String,
    @SerialName("createdAt")
    val createdAt: String,
    @SerialName("updatedAt")
    val updatedAt: String? = null,
)

// POST /api/v1/posts 요청 바디
@Serializable
data class CreatePostRequest(
    @SerialName("title")
    val title: String,
    @SerialName("content")
    val content: String,
    @SerialName("author")
    val author: String,
)

// PUT /api/v1/posts/{postId} 요청 바디
@Serializable
data class UpdatePostRequest(
    @SerialName("title")
    val title: String,
    @SerialName("content")
    val content: String,
)
