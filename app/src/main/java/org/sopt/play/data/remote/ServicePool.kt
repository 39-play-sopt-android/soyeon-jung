package org.sopt.play.data.remote

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.sopt.play.data.remote.service.MemberService
import org.sopt.play.data.remote.service.PostService
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

object ServicePool {
    private const val BASE_URL = "https://playsopt.o-r.kr/"

    private val json = Json {
        ignoreUnknownKeys = true
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor())
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            },
        )
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    val memberService: MemberService = retrofit.create(MemberService::class.java)
    val postService: PostService = retrofit.create(PostService::class.java)
}
