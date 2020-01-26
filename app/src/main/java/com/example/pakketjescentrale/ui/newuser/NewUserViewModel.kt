package com.example.pakketjescentrale.ui.newuser

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.pakketjescentrale.data.authentication.AuthenticationRepository
import com.example.pakketjescentrale.model.User

class NewUserViewModel(application: Application) : AndroidViewModel(application) {
    private val authenticationRepository = AuthenticationRepository()
    val error = MutableLiveData<String>()
    val success = MutableLiveData<Boolean>()

    fun saveNewUser(user: User){
        if(userIsValid(user)){
            authenticationRepository.registerNewUser(user)
        }
        success.value = true
    }

    private fun userIsValid(user: User): Boolean {
        return when {
            user == null -> {
                error.value = "User must not be null"
                false
            }
            user.userName.isBlank() -> {
                error.value = "Please fill in a valid email adress"
                false
            }
            !(user.userName.contains("@")) ->{
                error.value = "Please fill in a valid email adress"
                false
            }
            user.houseNumber.toString().isBlank() -> {
                error.value = "Please fill in a house number"
                false
            }
            user.passWord.isBlank() -> {
                error.value = "Please fill in a valid password"
                false
            }

            else -> true
        }
    }
}