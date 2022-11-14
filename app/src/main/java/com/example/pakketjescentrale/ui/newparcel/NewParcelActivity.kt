package com.example.pakketjescentrale.ui.newparcel

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.pakketjescentrale.MainActivity
import com.example.pakketjescentrale.R
import com.example.pakketjescentrale.data.parceldatabase.ParcelDataBaseApi
import com.example.pakketjescentrale.model.AuthenticationResponse
import com.example.pakketjescentrale.model.NewParcelRequest
import kotlinx.android.synthetic.main.activity_new_parcel.*
import kotlinx.android.synthetic.main.content_new_parcel.*
import kotlinx.android.synthetic.main.content_new_user.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewParcelActivity : AppCompatActivity() {

    private lateinit var newParcelViewModel: NewParcelViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_parcel)
        setSupportActionBar(toolbarParcel)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Aanmelden Nieuw Pakketje"

        val spinnerHuisnummer: Spinner = findViewById(R.id.spinnerHuisnummerParcel)
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.spinHuisnummer,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinnerHuisnummer.adapter = adapter
        }

        val spinnerToevoeging: Spinner = findViewById(R.id.spinnerToevoegingParcel)
        ArrayAdapter.createFromResource(
            this,
            R.array.spinToevoeging,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinnerToevoeging.adapter = adapter
        }

        initViews()
        initViewModel()
    }


    private fun initViewModel(){
        newParcelViewModel = ViewModelProviders.of(this).get(NewParcelViewModel::class.java)

        newParcelViewModel.error.observe(this, Observer { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        })

        newParcelViewModel.success.observe(this, Observer { success ->
            if (success) Toast.makeText(this, "Pakketje is geregistreerd", Toast.LENGTH_SHORT).show()
        })
    }

    private fun initViews(){
        btnSaveParcel.setOnClickListener {
            onSaveParcelClick()
        }
    }

    private fun onSaveParcelClick(){

        newParcelViewModel.saveNewParcel(etSender.text.toString(), etComment.text.toString(),
            etDatum.text.toString(), spinnerHuisnummerParcel.selectedItem.toString().toInt(), spinnerToevoegingParcel.selectedItem.toString())
    }

}
