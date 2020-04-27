package com.example.pakketjescentrale.ui.newuser

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.pakketjescentrale.R
import com.example.pakketjescentrale.model.NewUserRequest

import kotlinx.android.synthetic.main.activity_new_user.*
import kotlinx.android.synthetic.main.content_new_user.*

class NewUserActivity : AppCompatActivity() {

    private lateinit var newUserViewModel:NewUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_user)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Aanmelden Nieuwe Gebruiker"

        initViews()
        initViewModel()
    }

    private fun initViewModel(){
        newUserViewModel = ViewModelProviders.of(this).get(NewUserViewModel::class.java)

        newUserViewModel.error.observe(this, Observer { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        })

        newUserViewModel.success.observe(this, Observer { success ->
            if (success) finish()
        })
    }

    private fun initViews(){
        btnSaveUser.setOnClickListener {
            onSaveUserClick()
        }
    }

    private fun onSaveUserClick(){
        val newUser = NewUserRequest(
            etEmail.text.toString(),
            etPassword.text.toString(),
            arrayOf (spinHuisnummer).toString().toInt(),
            arrayOf (spinToevoeging).toString()
        )
        newUserViewModel.saveNewUser(newUser)
    }

}
