package com.example.client_android.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.client_android.R
import com.example.client_android.databinding.FragmentShopMenuBinding

class ShopMenuFragment : Fragment() {
    private var _binding: FragmentShopMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShopMenuBinding.inflate(layoutInflater, container, false)

        return binding.root
    }
}