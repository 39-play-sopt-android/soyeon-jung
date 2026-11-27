package org.sopt.play.data.remote.service

import org.sopt.play.data.remote.dto.AuthorizationCodeRequest
import org.sopt.play.data.remote.dto.AuthorizationCodeResponse
import org.sopt.play.data.remote.dto.BaseResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface MemberService {
    @POST("api/v1/members/authorization-code")
    suspend fun issueAuthorizationCode(
        @Body request: AuthorizationCodeRequest,
    ): BaseResponse<AuthorizationCodeResponse>
}
