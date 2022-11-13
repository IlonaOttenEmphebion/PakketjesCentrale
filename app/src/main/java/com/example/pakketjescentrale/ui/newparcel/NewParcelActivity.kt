package com.example.pakketjescentrale.ui.newparcel

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.pakketjescentrale.R
import com.example.pakketjescentrale.model.NewUserRequest
import com.example.pakketjescentrale.ui.newuser.NewParcelViewModel
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_new_user.*
import kotlinx.android.synthetic.main.content_new_user.*

class NewParcelActivity : AppCompatActivity() {

    private lateinit var newParcelViewModel: NewParcelViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_parcel)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Aanmelden Nieuw Pakketje"

//        val spinnerHuisnummer: Spinner = findViewById(R.id.spinnerHuisnummer)
//        // Create an ArrayAdapter using the string array and a default spinner layout
//        ArrayAdapter.createFromResource(
//            this,
//            R.array.spinHuisnummer,
//            android.R.layout.simple_spinner_item
//        ).also { adapter ->
//            // Specify the layout to use when the list of choices appears
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//            // Apply the adapter to the spinner
//            spinnerHuisnummer.adapter = adapter
//        }
//
//        val spinnerToevoeging: Spinner = findViewById(R.id.spinnerToevoeging)
//        ArrayAdapter.createFromResource(
//            this,
//            R.array.spinToevoeging,
//            android.R.layout.simple_spinner_item
//        ).also { adapter ->
//            // Specify the layout to use when the list of choices appears
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//            // Apply the adapter to the spinner
//            spinnerToevoeging.adapter = adapter
//        }
//
//        initViews()
//        initViewModel()
    }
//
//    private fun validateEmail(): Boolean {
//        val textInputEmail: TextInputLayout = findViewById(R.id.text_input_email)
//        val emailInput: String = textInputEmail.getEditText()?.getText().toString().trim()
//        return if (emailInput.isEmpty()) {
//            textInputEmail.setError("Field can't be empty")
//            false
//        } else {
//            textInputEmail.setError(null)
//            true
//        }
//    }
//
//    private fun validatePassword(): Boolean {
//        val textInputEmail: TextInputLayout = findViewById(R.id.text_input_password)
//        val emailInput: String = textInputEmail.getEditText()?.getText().toString().trim()
//        return if (emailInput.isEmpty()) {
//            textInputEmail.setError("Field can't be empty")
//            false
//        } else {
//            textInputEmail.setError(null)
//            true
//        }
//    }
//
//   public fun confirmInput() {
//      return if (!validateEmail() and !validatePassword()) {
//        } else {
//
//      }
//   }
//
//    private fun initViewModel(){
//        newParcelViewModel = ViewModelProviders.of(this).get(NewParcelViewModel::class.java)
//
//        newParcelViewModel.error.observe(this, Observer { message ->
//            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
//        })
//
//        newParcelViewModel.success.observe(this, Observer { success ->
//            if (success) Toast.makeText(this, "Registratie was succesvol", Toast.LENGTH_SHORT).show()
//        })
//    }
//
//    private fun initViews(){
//        btnSaveUser.setOnClickListener {
//            onSaveUserClick()
//        }
//    }
//
//    private fun onSaveUserClick(){
//        val newUser = NewUserRequest(
//            etEmail.text.toString(),
//            etPassword.text.toString(),
//            spinnerHuisnummer.toString().toInt(),
//            spinnerToevoeging.toString()
//        )
//        newParcelViewModel.saveNewUser(newUser)
//    }

}
