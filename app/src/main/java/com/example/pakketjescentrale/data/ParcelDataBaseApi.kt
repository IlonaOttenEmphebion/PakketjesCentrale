package com.example.pakketjescentrale.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ParcelDataBaseApi {
    companion object {
        // The base url off the api.
        private const val baseUrl = "https://parcels.yannickl88.nl/"


        /**
         * @return [ParcelDataBaseApiService] The service class off the retrofit client.
         */
        fun createApi(): ParcelDataBaseApiService {
            // Create an OkHttpClient to be able to make a log of the network traffic
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
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