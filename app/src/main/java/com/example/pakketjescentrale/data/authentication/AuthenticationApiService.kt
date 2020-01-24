package com.example.pakketjescentrale.data.authentication

import com.example.pakketjescentrale.model.AuthenticationResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthenticationApiService {

    @FormUrlEncoded
    @POST("/oauth/access_token")
    fun getOAuthToken(
        @Field ("grand_type") grandType:String,
        @Field ("scope") scope:String,
        @Field("username") username:String,
        @Field("password") password:String
    ): Call<AuthenticationResponse>


}