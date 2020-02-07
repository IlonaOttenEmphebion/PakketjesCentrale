package com.example.pakketjescentrale.data.registration

import okhttp3.Interceptor

class RegistrationInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {

        var request = chain.request()
        request = request.newBuilder()
            .header("Accept", "application/json")
            .build()

        return chain.proceed(request)
    }
}