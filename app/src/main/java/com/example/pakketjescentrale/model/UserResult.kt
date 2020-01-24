package com.example.pakketjescentrale.model

import com.google.gson.annotations.SerializedName

@Suppress("ArrayInDataClass")
data class UserResult (
    @SerializedName("users") var users: Array<User>
)