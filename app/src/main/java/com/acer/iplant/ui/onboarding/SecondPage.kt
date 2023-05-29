package com.acer.iplant.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager2.widget.ViewPager2
import com.acer.iplant.R

class SecondPage : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_second_page, container, false)

        val next = view.findViewById<ImageView>(R.id.btn_next2)
        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager2)

        next.setOnClickListener {
            viewPager?.currentItem = 2
        }

        return view
    }
}