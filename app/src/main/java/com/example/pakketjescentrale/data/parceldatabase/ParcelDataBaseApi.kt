package com.example.pakketjescentrale.data.parceldatabase

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ParcelDataBaseApi {
    companion object {
        // The base url off the api.
        private const val baseUrl = "https://parcels.yannickl88.nl/"
        lateinit var token:String

        /**
         * @return [ParcelDataBaseApiService] The service class off the retrofit client.
         */
        fun createApi(): ParcelDataBaseApiService {

            // Create an OkHttpClient to be able to make a log of the network traffic
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(
                    ParcelDataBaseInterceptor(
                        token
                    )
                )
                .build()

            // Create the Retrofit instance
            val parcelDataBaseApi = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            // Return the Retrofit MovieDataBaseApiService
            return parcelDataBaseApi.create(ParcelDataBaseApiService::class.java)
        }
    }
}