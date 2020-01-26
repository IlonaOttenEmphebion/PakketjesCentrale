package com.example.pakketjescentrale.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.pakketjescentrale.MainActivity
import com.example.pakketjescentrale.MainActivity.Companion.authenticationInfo
import com.example.pakketjescentrale.R
import com.example.pakketjescentrale.model.User
import com.example.pakketjescentrale.ui.newuser.NewUserActivity
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initButtonListeners()
    }

    private fun initButtonListeners(){
        btnLogin.setOnClickListener{onLogInBtnClick()}
        btnAanmelden.setOnClickListener { onAanmeldenBtnClick() }
    }

    private fun onLogInBtnClick(){
        homeViewModel.authenticate(etEmail.text.toString(), etPassword.text.toString())
        if(!authenticationInfo?.accessToken.isNullOrEmpty()){
            Toast.makeText(getActivity(),"Login was succesvol", Toast.LENGTH_SHORT).show();
        }
    }

    private fun onAanmeldenBtnClick(){
        val intent = Intent(this.context, NewUserActivity::class.java)
        startActivityForResult(
            intent,
            MainActivity.ADD_USER_REQUEST_CODE
        )
    }





}