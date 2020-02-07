package com.example.pakketjescentrale.data.registration

import com.example.pakketjescentrale.model.NewUserRequest
import com.example.pakketjescentrale.model.User

class RegistrationRepository {

    private val registrationApi: RegistrationApiService = RegistrationApi.createApi()

    fun registerNewUser(newUser: NewUserRequest) = registrationApi.registerNewUser(newUser)
}