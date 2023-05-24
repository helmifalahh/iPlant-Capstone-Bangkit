package com.acer.iplant.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.FragmentTransaction
import com.acer.iplant.R
import com.acer.iplant.customview.ButtonLoginCustomView
import com.acer.iplant.customview.PasswordCustomView
import com.acer.iplant.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    private lateinit var cvButton: ButtonLoginCustomView
    private lateinit var cvPassword: PasswordCustomView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        cvButton = binding.buttonLogin
        cvPassword = binding.edLoginPassword

        setButtonLoginCustomViewEnable()
        buttonLogin()

        binding.buttonLogin.setOnClickListener{
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }

        supportActionBar?.hide()
    }

    private fun setButtonLoginCustomViewEnable() {
        val result = cvPassword.text
        cvButton.isEnabled = result != null && result.toString().isNotEmpty() && result.length > 7
    }

    fun onClick(view: View) {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    fun buttonLogin(){
        cvPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                setButtonLoginCustomViewEnable()
            }
            override fun afterTextChanged(s: Editable) {
            }
        })
    }
}