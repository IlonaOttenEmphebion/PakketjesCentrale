package com.example.pakketjescentrale.data.authentication

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AuthenticationApi {
    companion object {
        // The base url off the api.
        private const val baseUrl = "https://parcels.yannickl88.nl/"


        /**
         * @return [AuthenticationApiService] The service class off the retrofit client.
         */
        fun createApi(): AuthenticationApiService {
            // Create an OkHttpClient to be able to make a log of the network traffic
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(
                    OAuthInterceptor(
                        "app",
                        "rvHGfH6wS6a3mmPC"
                    )
                )
                .build()

            // Create the Retrofit instance
            val authenticationApi = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            // Return the Retrofit MovieDataBaseApiService
            return authenticationApi.create(AuthenticationApiService::class.java)
        }
    }
}