package com.example.pakketjescentrale

import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.pakketjescentrale.model.AuthenticationResponse
import com.example.pakketjescentrale.model.User
import com.example.pakketjescentrale.ui.home.HomeFragment
import com.example.pakketjescentrale.ui.newuser.NewUserActivity
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.btnAanmelden as btnAanmelden1
import kotlinx.android.synthetic.main.fragment_home.btnLogin as btnLogin1

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val spinHuisnummer = resources.getStringArray(R.array.spinHuisnummer)
        val spinToevoeging = resources.getStringArray(R.array.spinToevoeging)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }

    companion object{
        //voorlopig even hier, wordt gebruikt bij authenticatie
        var localUser: User? = null
        var authenticationInfo: AuthenticationResponse? = null
        const val ADD_USER_REQUEST_CODE = 100
    }
}
