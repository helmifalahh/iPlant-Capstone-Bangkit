package com.acer.iplant.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.acer.iplant.R
import com.acer.iplant.databinding.ActivityStartBinding
import com.acer.iplant.ui.onboarding.FirstPage
import com.acer.iplant.ui.onboarding.FourthPage
import com.acer.iplant.ui.onboarding.SecondPage
import com.acer.iplant.ui.onboarding.ThirdPage

class StartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStartBinding

    val onBoardingFragment = OnBoardingFragment()
    val firstPage = FirstPage()
    val secondPage = SecondPage()
    val thirdPage = ThirdPage()
    val fourthPage = FourthPage()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        onBoarding()

        if(onBoardingIsFinished()){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        } else{
            onBoarding()
        }
    }

    private fun onBoarding(){
        fragmentDisplay(onBoardingFragment)
    }

    private fun fragmentDisplay(fragment: Fragment) = supportFragmentManager.beginTransaction().apply {
        replace(R.id.frame_start, fragment)
        commit()
    }

    private fun onBoardingIsFinished(): Boolean{
        val sharedPreferences = this.getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("finished",false)
    }
}