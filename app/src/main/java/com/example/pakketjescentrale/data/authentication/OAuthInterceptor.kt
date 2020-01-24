package com.example.pakketjescentrale.data.authentication

import okhttp3.Interceptor

class OAuthInterceptor(private val authorization_type: String, private val client_secret: String): Interceptor {

    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        var request = chain.request()
        request = request.newBuilder()
            .header("Authorization", "$authorization_type $client_secret")
            .build()

        return chain.proceed(request)
    }
}