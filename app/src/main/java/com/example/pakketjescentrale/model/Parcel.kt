package com.example.pakketjescentrale.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Parcel (
    @SerializedName("id") var parcelID: Number,
    @SerializedName("from") var receivedFrom : String,
    @SerializedName("comment") var comments: String,
    @SerializedName("location") var location: ParcelLocation
): Parcelable