package com.acer.iplant.ui.article

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.acer.iplant.databinding.ActivityDetailArticleBinding
import com.acer.iplant.remote.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailArticleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailArticleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setTitle("Detail Article")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}