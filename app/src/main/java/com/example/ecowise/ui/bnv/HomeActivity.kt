package com.example.ecowise.ui.bnv

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.ecowise.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val fragment = findViewById<FragmentContainerView>(R.id.fr_bnv)
        val Bnv = findViewById<BottomNavigationView>(R.id.bnv_main)

        // Set up navigation
        val navController = fragment.findNavController()
        Bnv.setupWithNavController(navController)

//        navController.addOnDestinationChangedListener { _, destination, _ ->
//            if (destination.id == R.id.courseFragment2) { // Replace with your detail fragment's ID
//                Bnv.visibility = View.GONE
//            } else {
//                Bnv.visibility = View.VISIBLE
//            }
//        }

    }
}