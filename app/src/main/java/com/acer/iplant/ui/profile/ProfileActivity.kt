package com.acer.iplant.ui.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.ui.res.stringResource
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.acer.iplant.R
import com.acer.iplant.databinding.ActivityProfileBinding
import com.acer.iplant.helper.SaveDark
import com.acer.iplant.ui.LoginActivity
import com.acer.iplant.ui.StartActivity
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

private val Context.settingDataStore by preferencesDataStore("settings")
class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private lateinit var mAuth : FirebaseAuth
    private lateinit var builder: AlertDialog.Builder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAuth = FirebaseAuth.getInstance()

        builder = AlertDialog.Builder(this)

        darkMode()
        displayUser()
        changeLanguage()
        checkAuth()

        binding.btnLogout.setOnClickListener{
            builder.setTitle(R.string.alert_logout)
                .setMessage(R.string.dialog_logout)
                .setCancelable(true)
                .setPositiveButton(R.string.logout){ dialogInterface,it ->
                    logout()
                }
                .setNegativeButton(R.string.not_logout){ dialogInterface,it ->
                    dialogInterface.cancel()
                }
                .show()
        }

        supportActionBar?.hide()
    }

    private fun displayUser() {
        val currentUser = mAuth.currentUser

        binding.user.text = currentUser?.displayName
        binding.textView3.text = currentUser?.email
        Glide.with(this).load(currentUser?.photoUrl).into(
            binding.photoUserIplant
        )
    }

    private fun logout(){
        mAuth.signOut()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
        finishAffinity()
    }

    private fun checkAuth(){
        mAuth = Firebase.auth
        var firebaseUser = mAuth.currentUser

        if (firebaseUser == null){
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }
    }

    private fun changeLanguage() {
        binding.changeLanguage.setOnClickListener {
            startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
        }
    }

    private fun darkMode() {
        val pref = SaveDark.getInstance(settingDataStore)
        val mainViewModel = ViewModelProvider(this, ProfileViewModel.Factory(pref)).get(
            ProfileViewModel::class.java
        )
        mainViewModel.getTheme().observe(this) {
            if (it) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            binding.switchDark.isChecked = it
        }

        binding.switchDark.setOnCheckedChangeListener { _, isChecked ->
            mainViewModel.saveTheme(isChecked)
        }
    }
}