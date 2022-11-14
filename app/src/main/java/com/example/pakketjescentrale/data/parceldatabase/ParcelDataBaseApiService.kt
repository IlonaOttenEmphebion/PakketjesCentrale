package com.example.pakketjescentrale.data.parceldatabase

import com.example.pakketjescentrale.model.MessageResult
import com.example.pakketjescentrale.model.NewParcelRequest
import com.example.pakketjescentrale.model.Parcel
import com.example.pakketjescentrale.model.SearchUser
import retrofit2.Call
import retrofit2.http.*

interface ParcelDataBaseApiService {

    @GET("/api/v1/parcels/owned")
    fun getMyParcels(): Call<Array<Parcel>>

    @GET("/api/v1/parcels/received")
    fun getParcelsReceived(): Call<Array<Parcel>>

    @POST("/api/v1/parcels/received")
    fun registerReceivedParcel(@Body parcel: NewParcelRequest): Call<Any>

    @PUT("/api/v1/parcels/{parcel_id}/pickup")
    fun updateParcel(@Path("parcel_id") parcel_id: Number, parcel: Parcel)

    @GET("/api/v1/parcels/{parcel_id}/messages")
    fun getMessagesForThisParcel(@Path("parcel-id") parcel_id: Number):Call<MessageResult>

    @POST("/api/v1/parcels/{parcel_id}/messages")
    fun saveMessageForThisParcel(@Path("parcel-id") parcel_id: Number, comment: String)

    @GET("/api/v1/user/search")
    fun getUserSearch(@Query("query") query: String): Call<Array<SearchUser>>
}