package com.acer.iplant.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.acer.iplant.customview.ButtonRegisterCustomView
import com.acer.iplant.customview.PasswordCustomView
import com.acer.iplant.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private val binding: ActivityRegisterBinding by lazy {
        ActivityRegisterBinding.inflate(layoutInflater)
    }

    private lateinit var cvButton: ButtonRegisterCustomView
    private lateinit var cvPassword: PasswordCustomView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.hide()

        cvButton = binding.buttonLogin
        cvPassword = binding.edRegisterPassword

        setButtonRegistCustomViewEnable()

        cvPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                setButtonRegistCustomViewEnable()
            }
            override fun afterTextChanged(s: Editable) {
            }
        })
    }

    private fun setButtonRegistCustomViewEnable() {
        val result = cvPassword.text
        cvButton.isEnabled = result != null && result.toString().isNotEmpty() && result.length > 7
    }

    fun onClick(view: View) {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}