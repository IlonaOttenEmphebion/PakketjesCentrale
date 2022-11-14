package com.example.pakketjescentrale.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchUser (
    @SerializedName("house_number") var houseNumber: Number,
    @SerializedName("house_number_addition") var numberAddition: String,
    @SerializedName("id") var id:Number
    ): Parcelable