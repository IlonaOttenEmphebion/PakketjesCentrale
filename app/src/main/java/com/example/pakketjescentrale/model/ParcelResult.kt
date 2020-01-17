package com.example.pakketjescentrale.model

import com.google.gson.annotations.SerializedName

@Suppress("ArrayInDataClass")
data class ParcelResult (
    @SerializedName("results") var parcels: Array<Parcel>
)