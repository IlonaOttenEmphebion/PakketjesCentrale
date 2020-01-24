package com.example.pakketjescentrale.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User (
    @SerializedName("email") var userName: String,
    @SerializedName("password") var passWord: String,
    @SerializedName("house_number") var houseNumber: Number,
    @SerializedName("house_number_addition") var numberAddition: String,
    @SerializedName("id") var id:Number
    ): Parcelable