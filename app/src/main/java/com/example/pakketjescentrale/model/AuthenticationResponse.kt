package com.example.pakketjescentrale.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AuthenticationResponse (
    @SerializedName ("token_type") var tokenType:String,
    @SerializedName ("expires_in") var expiresIn: Number,
    @SerializedName ("access_token") var accessToken: String,
    @SerializedName ("refresh_token") var refreshToken: String
): Parcelable