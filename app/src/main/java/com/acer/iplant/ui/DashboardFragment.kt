package com.acer.iplant.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.acer.iplant.R
import com.acer.iplant.databinding.FragmentDashboardBinding
import com.acer.iplant.helper.rotateBitmap
import com.acer.iplant.ui.CameraActivity.Companion.CAMERA_X_RESULT
import com.bumptech.glide.Glide
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.firebase.auth.FirebaseAuth
import java.io.File

class DashboardFragment : Fragment() {

    private var _binding : FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private lateinit var mAuth : FirebaseAuth

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

        binding.apply {
            moveToArticle.setOnClickListener {
                onClick(view)
            }
            photoUserIplant.setOnClickListener{
                val profileFragment = ProfileFragment()
                val transaction : FragmentTransaction = requireFragmentManager().beginTransaction()
                transaction.replace(R.id.frame_dashboard, profileFragment)
                transaction.commit()
            }
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
        val articleFragment = ArticleFragment()
        val transaction : FragmentTransaction = fragmentManager!!.beginTransaction()
        transaction.replace(R.id.frame_dashboard, articleFragment)
        transaction.commit()
    }
}