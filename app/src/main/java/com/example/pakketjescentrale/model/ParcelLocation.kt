package com.example.pakketjescentrale.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ParcelLocation (
    @SerializedName("identifier") var location: String,
    @SerializedName("user_id") var locationUserID: Number
):Parcelable