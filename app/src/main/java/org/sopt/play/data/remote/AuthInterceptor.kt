package org.sopt.play.data.remote

import okhttp3.Interceptor
import okhttp3.Response
import org.sopt.play.core.session.UserSession

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val authorizationCode = UserSession.authorizationCode
            ?: return chain.proceed(chain.request())

        val request = chain.request().newBuilder()
            .addHeader("Authorization", authorizationCode)
            .build()

        return chain.proceed(request)
    }
}
