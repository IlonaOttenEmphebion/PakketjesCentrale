package com.example.pakketjescentrale.data.parceldatabase

import okhttp3.Interceptor

class ParcelDataBaseInterceptor(private val autheticationToken:String): Interceptor {

    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        var request = chain.request()
        request = request.newBuilder()
            .header("access_token", "$autheticationToken")
            .build()

        return chain.proceed(request)
    }
}