package com.example.pakketjescentrale.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Message (
    @SerializedName("comment") var messageText:String
): Parcelable