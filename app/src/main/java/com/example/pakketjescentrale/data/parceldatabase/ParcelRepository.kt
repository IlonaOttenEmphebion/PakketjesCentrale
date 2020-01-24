package com.example.pakketjescentrale.data.parceldatabase

import com.example.pakketjescentrale.data.parceldatabase.ParcelDataBaseApi
import com.example.pakketjescentrale.data.parceldatabase.ParcelDataBaseApiService
import com.example.pakketjescentrale.model.ParcelResult
import retrofit2.Call

class ParcelRepository {
    private val parcelApi: ParcelDataBaseApiService = ParcelDataBaseApi.createApi()

    fun getMyParcels(): Call<ParcelResult> = parcelApi.getMyParcels()
}