package com.example.pakketjescentrale.data.authentication

import okhttp3.Credentials
import okhttp3.Interceptor

class OAuthInterceptor(private val client_name: String, private val client_secret: String): Interceptor {

    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        var credential = Credentials.basic(client_name, client_secret)
        var request = chain.request()
        request = request.newBuilder()
            .header("Authorization", credential)
            .build()

        return chain.proceed(request)
    }
}