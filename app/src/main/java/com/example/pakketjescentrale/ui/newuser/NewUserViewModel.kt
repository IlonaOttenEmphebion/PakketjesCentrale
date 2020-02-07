package com.example.pakketjescentrale.ui.newuser

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.pakketjescentrale.MainActivity.Companion.localUser
import com.example.pakketjescentrale.data.registration.RegistrationRepository
import com.example.pakketjescentrale.model.NewUserRequest
import com.example.pakketjescentrale.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewUserViewModel(application: Application) : AndroidViewModel(application) {
    private val registrationRepository = RegistrationRepository()
    lateinit var returnedUserInfo:User
    val error = MutableLiveData<String>()
    val success = MutableLiveData<Boolean>()

    fun saveNewUser(user: NewUserRequest){
        if(userIsValid(user)){
            registrationRepository.registerNewUser(user).enqueue(object :
                Callback<User> {
                override fun onResponse(
                    call: Call<User>,
                    response: Response<User>
                ) {
                    if (response.isSuccessful) {
                        println("User registered")
                        returnedUserInfo = response.body()!!
                        success.value = true
                        localUser = returnedUserInfo

                    } else {
                        println(response.errorBody()?.string())
                        success.value = false
                        error.value = "An error occurred: ${response.errorBody().toString()}"
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    error.value = t.message
                    success.value = false
                }
            })
        }
    }

    private fun userIsValid(newUser: NewUserRequest): Boolean {
        return when {
            newUser == null -> {
                error.value = "User must not be null"
                false
            }
            newUser.userName.isBlank() -> {
                error.value = "Please fill in a valid email adress"
                false
            }
            !(newUser.userName.contains("@")) ->{
                error.value = "Please fill in a valid email adress"
                false
            }
            newUser.houseNumber.toString().isBlank() -> {
                error.value = "Please fill in a house number"
                false
            }
            newUser.passWord.isBlank() -> {
                error.value = "Please fill in a valid password"
                false
            }

            else -> true
        }
    }
}