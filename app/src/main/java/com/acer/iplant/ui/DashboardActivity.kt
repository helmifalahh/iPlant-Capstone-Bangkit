package com.acer.iplant.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.acer.iplant.R
import com.acer.iplant.databinding.ActivityDashboardBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding

    val dashboardFragment = DashboardFragment()
    val articleFragment = ArticleFragment()
    val profileFragment = ProfileFragment()

    private lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        bottomNavigation()
        fragmentDisplay(dashboardFragment)
    }

    private fun bottomNavigation(){
        bottomNavigation = binding.bottomNavigation
        bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home -> fragmentDisplay(dashboardFragment)
                R.id.article -> fragmentDisplay(articleFragment)
                R.id.profile -> fragmentDisplay(profileFragment)
            }
            true
        }
    }

    private fun fragmentDisplay(fragment: Fragment) = supportFragmentManager.beginTransaction().apply {
        replace(R.id.frame_dashboard, fragment)
        commit()
    }
}