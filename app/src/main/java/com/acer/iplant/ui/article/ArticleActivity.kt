package com.acer.iplant.ui.article

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.acer.iplant.R
import com.acer.iplant.databinding.ActivityArticleBinding
import com.acer.iplant.remote.ApiConfig
import com.acer.iplant.remote.ApiService
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.firebase.database.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArticleBinding
    private val list = ArrayList<ArticleResponseItem>()

    private lateinit var shimmerView : ShimmerFrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setTitle("Article")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        shimmerView = binding.shimmerArticle

        showArticle()

        binding.apply {
            rvArticle.setHasFixedSize(true)
            rvArticle.layoutManager = LinearLayoutManager(this@ArticleActivity)
        }
    }

    private fun showArticle(){
        ApiConfig.apiInstance.getArticle().enqueue(object: Callback<ArrayList<ArticleResponseItem>>{
            override fun onResponse(
                call: Call<ArrayList<ArticleResponseItem>>,
                response: Response<ArrayList<ArticleResponseItem>>
            ) {
                shimmerView.stopShimmer()
                shimmerView.visibility = View.GONE
                response.body()?.let { list.addAll(it) }
                val adapter = ArticleAdapter(list)
                binding.rvArticle.adapter = adapter
            }

            override fun onFailure(call: Call<ArrayList<ArticleResponseItem>>, t: Throwable) {

            }

        })
    }
}