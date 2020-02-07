package com.example.pakketjescentrale.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pakketjescentrale.MainActivity.Companion.authenticationInfo
import com.example.pakketjescentrale.MainActivity.Companion.localUser
import com.example.pakketjescentrale.data.authentication.AuthenticationRepository
import com.example.pakketjescentrale.data.parceldatabase.ParcelDataBaseApi
import com.example.pakketjescentrale.data.registration.RegistrationRepository
import com.example.pakketjescentrale.model.AuthenticationResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val authenticationRepository =
        AuthenticationRepository()
    lateinit var authenticationResponse: AuthenticationResponse
    val error = MutableLiveData<String>()

    fun authenticate(userName: String, passWord: String) {
        //Voordat de DataBaseApi connectie opgezet wordt, is authenticatie nodig om het benodigde token te
        //krijgen tbv database requests. N.B.: dit moet aangeroepen worden nadat de user heeft ingelogd.

        authenticationRepository.getOAuthToken(
            userName,
            passWord
        ).enqueue(object :
            Callback<AuthenticationResponse> {
            override fun onResponse(
                call: Call<AuthenticationResponse>,
                response: Response<AuthenticationResponse>
            ) {
                if (response.isSuccessful) {
                    println("YAY")
                    authenticationResponse = response.body()!!
                    ParcelDataBaseApi.token =
                        authenticationResponse.accessToken
                    authenticationInfo = authenticationResponse

                } else {
                    println(response.errorBody()?.string())
                    error.value = "An error occurred: ${response.errorBody().toString()}"
                }
            }

            override fun onFailure(call: Call<AuthenticationResponse>, t: Throwable) {
                error.value = t.message
            }
        })

    }

}