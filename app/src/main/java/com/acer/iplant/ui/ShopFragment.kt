package com.acer.iplant.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.acer.iplant.databinding.FragmentShopBinding
import com.acer.iplant.ui.shopcompose.ShopActivity

class ShopFragment : Fragment() {
    private var _binding : FragmentShopBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentShopBinding.inflate(inflater, container, false)
        val view : View = binding.root

        binding.button.setOnClickListener {
            val intent = Intent(requireActivity(), ShopActivity::class.java)
            startActivity(intent)
        }

        return view
    }
}