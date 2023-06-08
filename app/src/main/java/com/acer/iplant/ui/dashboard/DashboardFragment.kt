package com.acer.iplant.ui.dashboard

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.acer.iplant.R
import com.acer.iplant.databinding.FragmentDashboardBinding
import com.acer.iplant.remote.ApiConfig
import com.acer.iplant.ui.article.ArticleActivity
import com.acer.iplant.ui.article.ArticleAdapter
import com.acer.iplant.ui.article.ArticleResponseItem
import com.acer.iplant.ui.profile.ProfileActivity
import com.bumptech.glide.Glide
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardFragment : Fragment() {

    private var _binding : FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private lateinit var mAuth : FirebaseAuth
    private val list = ArrayList<ArticleResponseItem>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val view : View = binding.root

        mAuth = FirebaseAuth.getInstance()

        val imageSlider = view.findViewById<ImageSlider>(R.id.image_slider)
        val imageList = ArrayList<SlideModel>()
        imageList.add(SlideModel("https://bit.ly/2YoJ77H"))
        imageList.add(SlideModel("https://bit.ly/2BteuF2"))
        imageList.add(SlideModel(R.drawable.dash1))
        imageList.add(SlideModel(R.drawable.dash2))

        imageSlider.setImageList(imageList, ScaleTypes.CENTER_CROP)

        displayUser()
        showArticle()

        binding.apply {
            moveToArticle.setOnClickListener {
                onClick(view)
            }
            photoUserIplant.setOnClickListener{
                val intent = Intent(requireActivity(), ProfileActivity::class.java)
                startActivity(intent)
            }
            rvArticle.setHasFixedSize(true)
            rvArticle.layoutManager = LinearLayoutManager(requireContext())
        }

        return view
    }

    private fun displayUser() {
        val currentUser = mAuth.currentUser

        binding.userIplant.text = currentUser?.displayName
        Glide.with(this).load(currentUser?.photoUrl).into(
            binding.photoUserIplant
        )
    }

    @SuppressLint("UseRequireInsteadOfGet")
    fun onClick(view: View) {
        val intent = Intent(requireActivity(), ArticleActivity::class.java)
        startActivity(intent)
    }

    private fun showArticle(){
        ApiConfig.apiInstance.getArticle().enqueue(object:
            Callback<ArrayList<ArticleResponseItem>> {
            override fun onResponse(
                call: Call<ArrayList<ArticleResponseItem>>,
                response: Response<ArrayList<ArticleResponseItem>>
            ) {
                response.body()?.let { list.addAll(it) }
                val adapter = ArticleDashboardAdapter(list)
                binding.rvArticle.adapter = adapter
            }

            override fun onFailure(call: Call<ArrayList<ArticleResponseItem>>, t: Throwable) {

            }

        })
    }
}