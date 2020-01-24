package com.example.pakketjescentrale.data.authentication

import com.example.pakketjescentrale.model.AuthenticationResponse
import retrofit2.Call

class AuthenticationRepository {

    private val authenticationApi: AuthenticationApiService = AuthenticationApi.createApi()


    fun getOAuthToken(userName:String, password:String):Call<AuthenticationResponse> = authenticationApi.getOAuthToken(
        grandType,
        scope,userName, password)

    companion object{
        private const val grandType:String = "password"
        private const val scope:String = "info parcel"
    }
}