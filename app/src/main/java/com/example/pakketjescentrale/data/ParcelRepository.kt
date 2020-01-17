package com.example.pakketjescentrale.data

import com.example.pakketjescentrale.model.ParcelResult
import retrofit2.Call

class ParcelRepository {
    private val parcelApi:ParcelDataBaseApiService = ParcelDataBaseApi.createApi()

    fun getMyParcels(): Call<ParcelResult> = parcelApi.getMyParcels()
}