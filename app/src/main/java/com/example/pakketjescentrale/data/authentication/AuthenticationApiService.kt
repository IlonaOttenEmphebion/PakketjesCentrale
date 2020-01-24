package com.example.pakketjescentrale.data.authentication

import com.example.pakketjescentrale.model.AuthenticationResponse
import com.example.pakketjescentrale.model.User
import com.example.pakketjescentrale.model.UserResult
import retrofit2.Call
import retrofit2.http.*

interface AuthenticationApiService {

    @FormUrlEncoded
    @POST("/oauth/access_token")
    fun getOAuthToken(
        @Field ("grand_type") grandType:String,
        @Field ("scope") scope:String,
        @Field("username") username:String,
        @Field("password") password:String
    ): Call<AuthenticationResponse>

    @POST("/api/v1/user/register")
    fun registerNewUser(user:User)

    @GET("/api/v1/user/search")
    fun searchUsers(@Query(value = "query", encoded = true) queryTerms: String):Call<UserResult>

    @GET("/api/v1/user/me")
    fun getMyUserInformation():Call<User>

}