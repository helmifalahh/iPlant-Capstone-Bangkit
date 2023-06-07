package com.acer.iplant.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.acer.iplant.R
import com.acer.iplant.databinding.ActivityDashboardBinding
import com.acer.iplant.helper.SaveDark
import com.acer.iplant.ui.dashboard.DashboardFragment
import com.acer.iplant.ui.profile.ProfileViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

private val Context.settingDataStore by preferencesDataStore("settings")
class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding

    val dashboardFragment = DashboardFragment()
    val shopFragment = ShopFragment()

    private lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.fabCamera.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            startActivity(intent)
        }

        bottomNavigation()
        darkMode()
        fragmentDisplay(dashboardFragment)
    }

    private fun bottomNavigation(){
        bottomNavigation = binding.bottomNavigation
        bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home -> fragmentDisplay(dashboardFragment)
                R.id.shop -> fragmentDisplay(shopFragment)
            }
            true
        }
    }

    private fun fragmentDisplay(fragment: Fragment) = supportFragmentManager.beginTransaction().apply {
        replace(R.id.frame_dashboard, fragment)
        commit()
    }

    private fun darkMode() {
        val pref = SaveDark.getInstance(settingDataStore)
        val mainViewModel = ViewModelProvider(this, ProfileViewModel.Factory(pref)).get(
            ProfileViewModel::class.java
        )
        mainViewModel.getTheme().observe(this) { isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }
}