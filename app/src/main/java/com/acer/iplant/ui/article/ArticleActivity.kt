package com.acer.iplant.ui.article

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.acer.iplant.R
import com.acer.iplant.databinding.ActivityArticleBinding
import com.google.firebase.database.*

class ArticleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArticleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setTitle("Article")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


}