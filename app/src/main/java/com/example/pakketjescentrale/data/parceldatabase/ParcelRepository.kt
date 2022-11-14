package com.example.pakketjescentrale.data.parceldatabase

import com.example.pakketjescentrale.model.*
import retrofit2.Call

class ParcelRepository {
    private val parcelApi: ParcelDataBaseApiService = ParcelDataBaseApi.createApi()

    fun getMyParcels(): Call<Array<Parcel>> = parcelApi.getMyParcels()

    fun getParcelsReceived():Call<Array<Parcel>> = parcelApi.getParcelsReceived()

    fun registerReceivedParcel(parcel: NewParcelRequest) = parcelApi.registerReceivedParcel(parcel)

    fun updateParcel(parcel: Parcel) = parcelApi.updateParcel(parcel.parcelID, parcel)

    fun getMessagesForThisParcel(parcelId: Number):Call<MessageResult> = parcelApi.getMessagesForThisParcel(parcelId)

    fun saveMessageForThisParcel(parcelId: Number, message:String) = parcelApi.saveMessageForThisParcel(parcelId, message)

    fun searchUser(query: String): Call<Array<SearchUser>> = parcelApi.getUserSearch(query)
}