package com.example.finalevo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.finalevo.databinding.ActivityMainBinding
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {


    private lateinit var navController : NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when(destination.id) {
                R.id.movieSearchFragment -> Log.d("salut", "salut")
                R.id.topRatedFragment -> Log.d("salut", "salut")
            }
        }

        var bottomNavigationView = binding.bottomNavigationView
        NavigationUI.setupWithNavController(bottomNavigationView, navController)
    }


}

