package com.example.pakketjescentrale.data.registration

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegistrationApi {
    companion object {
        // The base url off the api.
        private const val baseUrl = "https://parcels.yannickl88.nl/"


        /**
         * @return [RegistrationApiService] The service class off the retrofit client.
         */
        fun createApi(): RegistrationApiService {
            // Create an OkHttpClient to be able to make a log of the network traffic
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(
                    RegistrationInterceptor()
                )
                .build()

            // Create the Retrofit instance
            val registrationApi = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            // Return the Retrofit RegistrationApiService
            return registrationApi.create(RegistrationApiService::class.java)
        }
    }
}