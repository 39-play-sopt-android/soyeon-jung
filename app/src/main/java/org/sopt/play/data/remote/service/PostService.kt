package org.sopt.play.data.remote.service

import org.sopt.play.data.remote.dto.BaseResponse
import org.sopt.play.data.remote.dto.CreatePostRequest
import org.sopt.play.data.remote.dto.PostResponse
import org.sopt.play.data.remote.dto.PostSliceResponse
import org.sopt.play.data.remote.dto.UpdatePostRequest
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface PostService {
    @GET("api/v1/posts")
    suspend fun getPosts(
        @Query("keyword") keyword: String? = null,
    ): BaseResponse<PostSliceResponse>

    @POST("api/v1/posts")
    suspend fun createPost(
        @Body request: CreatePostRequest,
    ): BaseResponse<PostResponse>

    @PUT("api/v1/posts/{postId}")
    suspend fun updatePost(
        @Path("postId") postId: Long,
        @Body request: UpdatePostRequest,
    ): BaseResponse<PostResponse>

    @DELETE("api/v1/posts/{postId}")
    suspend fun deletePost(
        @Path("postId") postId: Long,
    )
}
