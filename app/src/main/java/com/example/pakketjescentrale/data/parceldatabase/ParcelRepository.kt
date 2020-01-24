package com.example.pakketjescentrale.data.parceldatabase

import com.example.pakketjescentrale.data.parceldatabase.ParcelDataBaseApi
import com.example.pakketjescentrale.data.parceldatabase.ParcelDataBaseApiService
import com.example.pakketjescentrale.model.MessageResult
import com.example.pakketjescentrale.model.Parcel
import com.example.pakketjescentrale.model.ParcelResult
import retrofit2.Call

class ParcelRepository {
    private val parcelApi: ParcelDataBaseApiService = ParcelDataBaseApi.createApi()

    fun getMyParcels(): Call<ParcelResult> = parcelApi.getMyParcels()

    fun getParcelsReceived():Call<ParcelResult> = parcelApi.getParcelsReceived()

    fun registerReceivedParcel(parcel: Parcel) = parcelApi.registerReceivedParcel(parcel)

    fun updateParcel(parcel: Parcel) = parcelApi.updateParcel(parcel.parcelID, parcel)

    fun getMessagesForThisParcel(parcelId: Number):Call<MessageResult> = parcelApi.getMessagesForThisParcel(parcelId)

    fun saveMessageForThisParcel(parcelId: Number, message:String) = parcelApi.saveMessageForThisParcel(parcelId, message)
}