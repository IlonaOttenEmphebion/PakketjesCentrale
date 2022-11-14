package com.example.pakketjescentrale.ui.newparcel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.pakketjescentrale.data.parceldatabase.ParcelRepository
import com.example.pakketjescentrale.model.NewParcelRequest
import com.example.pakketjescentrale.model.Parcel
import com.example.pakketjescentrale.model.SearchUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewParcelViewModel(application: Application) : AndroidViewModel(application) {
    val error = MutableLiveData<String>()
    val success = MutableLiveData<Boolean>()

    fun saveNewParcel(sender: String, comment: String, date: String, house_number: Number, house_number_addition: String){
        val parcelRepository = ParcelRepository()

        parcelRepository.searchUser(house_number.toString()).enqueue(object :
            Callback<Array<SearchUser>> {
            override fun onResponse(call: Call<Array<SearchUser>>, response: Response<Array<SearchUser>>) {
                if (response.isSuccessful) {
                    val users = response.body()!!
                    var user: SearchUser? = null

                    // Loop over de users
                    for (i in users) {
                        if(i.numberAddition == house_number_addition && i.houseNumber.toInt() == house_number.toInt()){
                            user = i
                        }
                    }

                    if (user != null) {
                        val newParcel = NewParcelRequest(
                            sender,
                            comment,
                            user.id,
                            date
                        )

                        if (parcelIsValid(newParcel)) {
                            parcelRepository.registerReceivedParcel(newParcel).enqueue(object :
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
                                        error.value =
                                            "An error occurred: ${response.errorBody().toString()}"
                                    }
                                }

                                override fun onFailure(call: Call<Any>, t: Throwable) {
                                    error.value = t.message
                                    success.value = false
                                }
                            })
                        }
                    } else {
                        error.value = "Niemand op dit huisnummer geregistreerd"
                        success.value = false
                    }
                }
            }

            override fun onFailure(call: Call<Array<SearchUser>>, t: Throwable) {
                error.value = t.message
                success.value = false
            }
        })
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