package com.example.pakketjescentrale.data.authentication

import com.example.pakketjescentrale.model.AuthenticationResponse
import com.example.pakketjescentrale.model.User
import com.example.pakketjescentrale.model.UserResult
import retrofit2.Call

class AuthenticationRepository {

    private val authenticationApi: AuthenticationApiService = AuthenticationApi.createApi()


    fun getOAuthToken(userName:String, password:String):Call<AuthenticationResponse> = authenticationApi.getOAuthToken(
        grandType,
        scope,userName, password)

    fun registerNewUser(user: User) = authenticationApi.registerNewUser(user)

    fun searchUsers(query:String):Call<UserResult> = authenticationApi.searchUsers(query)

    fun getMyUserInformation():Call<User> = authenticationApi.getMyUserInformation()

    companion object{
        private const val grandType:String = "password"
        private const val scope:String = "info parcel"
    }
}