package com.acer.iplant.ui.onboarding

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.acer.iplant.R
import com.acer.iplant.databinding.FragmentFourthPageBinding
import com.acer.iplant.ui.LoginActivity

class FourthPage : Fragment() {

    private var _binding : FragmentFourthPageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFourthPageBinding.inflate(inflater, container, false)
        val view : View = binding.root

        binding.btnNext4.setOnClickListener{
            val intent = Intent(activity, LoginActivity::class.java)
            activity?.startActivity(intent)
            onBoardingIsFinished()
        }

        return view
    }

    private fun onBoardingIsFinished(){

        val sharedPreferences = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("finished",true)
        editor.apply()
    }
}