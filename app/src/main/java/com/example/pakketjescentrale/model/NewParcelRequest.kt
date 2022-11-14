package com.example.pakketjescentrale.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewParcelRequest (
    @SerializedName("sender") var sender: String,
    @SerializedName("comment") var comment: String,
    @SerializedName("owner_id") var owner_id: Number,
    @SerializedName("date") var date: String
): Parcelable