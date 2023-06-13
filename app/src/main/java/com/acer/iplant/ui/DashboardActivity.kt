package com.acer.iplant.ui

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

private val Context.settingDataStore by preferencesDataStore("settings")
class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding

    val dashboardFragment = DashboardFragment()
    val shopFragment = ShopFragment()

    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.fabCamera.setOnClickListener {
            val intent = Intent(this, MediaActivity::class.java)
            startActivity(intent)
        }

        val prefs: SharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE)
        val firstTime: Boolean = prefs.getBoolean("firstTime", true)

        if (firstTime){ infoDialog() }

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

    fun infoDialog(){
        val dialog = layoutInflater.inflate(R.layout.info_potato, null)

        val infoDialog = Dialog(this)
        infoDialog.setContentView(dialog)

        infoDialog.setCancelable(true)
        infoDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        infoDialog.show()

        val done = dialog.findViewById<Button>(R.id.button_close)
        done.setOnClickListener {
            infoDialog.dismiss()
        }

        val prefs: SharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE)
        val editor: SharedPreferences.Editor = prefs.edit()
        editor.putBoolean("firstTime", false)
        editor.apply()
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