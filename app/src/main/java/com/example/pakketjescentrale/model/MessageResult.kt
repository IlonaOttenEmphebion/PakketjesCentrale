package com.example.pakketjescentrale.model

import com.google.gson.annotations.SerializedName

@Suppress("ArrayInDataClass")
data class MessageResult (
    @SerializedName("messages") var messages: Array<Message>
)