package com.example.pakketjescentrale.data.registration

import com.example.pakketjescentrale.model.NewUserRequest
import com.example.pakketjescentrale.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RegistrationApiService {

    @POST("/api/v1/user/register")
    fun registerNewUser(@Body newUser: NewUserRequest): Call<User>
}