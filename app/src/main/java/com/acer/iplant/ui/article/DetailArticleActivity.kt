package com.acer.iplant.ui.article

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.acer.iplant.databinding.ActivityDetailArticleBinding
import com.acer.iplant.remote.ApiConfig
import com.bumptech.glide.Glide
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

        showDetail()
    }

    private fun showDetail(){
        val title = intent.getStringExtra(TITLE_EXTRA)
        val description = intent.getStringExtra(DESCRIPTION_EXTRA)
        val image = intent.getStringExtra(IMAGE_URL_EXTRA)

        binding.tvTitle.text = title
        binding.tvDesc.text = description
        Glide.with(this)
            .load(image)
            .into(binding.imgPost)
    }

    companion object {
        const val TITLE_EXTRA = "title_extra"
        const val DESCRIPTION_EXTRA = "desc_extra"
        const val IMAGE_URL_EXTRA = "img_extra"
    }
}