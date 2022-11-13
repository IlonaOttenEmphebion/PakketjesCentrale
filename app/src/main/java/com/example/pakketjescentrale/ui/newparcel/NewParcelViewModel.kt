package com.example.pakketjescentrale.ui.newparcel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.pakketjescentrale.MainActivity.Companion.localUser
import com.example.pakketjescentrale.data.parceldatabase.ParcelRepository
import com.example.pakketjescentrale.data.registration.RegistrationRepository
import com.example.pakketjescentrale.model.NewParcelRequest
import com.example.pakketjescentrale.model.NewUserRequest
import com.example.pakketjescentrale.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewParcelViewModel(application: Application) : AndroidViewModel(application) {
    private val parcelRepository = ParcelRepository()
    val error = MutableLiveData<String>()
    val success = MutableLiveData<Boolean>()

    fun saveNewParcel(parcel: NewParcelRequest){
        if(parcelIsValid(parcel)){
            parcelRepository.registerReceivedParcel(parcel).enqueue(object :
                Callback<Any> {
                override fun onResponse(
                    call: Call<Any>,
                    response: Response<Any>
                ) {
                    if (response.isSuccessful) {
                        println("Parcel registered")
                        success.value = true

                    } else {
                        println(response.errorBody()?.string())
                        success.value = false
                        error.value = "An error occurred: ${response.errorBody().toString()}"
                    }
                }

                override fun onFailure(call: Call<Any>, t: Throwable) {
                    error.value = t.message
                    success.value = false
                }
            })
        }
    }

    private fun parcelIsValid(newParcel: NewParcelRequest): Boolean {
        return when {
            newParcel == null -> {
                error.value = "Parcel must not be null"
                false
            }
            newParcel.comment.isBlank() -> {
                error.value = "Please fill in a valid pickup date"
                false
            }
            newParcel.date.isBlank() ->{
                error.value = "Please fill in a valid date"
                false
            }
            newParcel.sender.isBlank() -> {
                error.value = "Please fill in a sender, like bol.com"
                false
            }

            else -> true
        }
    }
}