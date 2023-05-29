package com.acer.iplant.ui.shopcompose.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.acer.iplant.ui.shopcompose.data.ShopRepository
import com.acer.iplant.ui.shopcompose.ui.screen.cart.CartViewModel
import com.acer.iplant.ui.shopcompose.ui.screen.detail.DetailProductViewModel
import com.acer.iplant.ui.shopcompose.ui.screen.home.HomeViewModel

class ViewModelFactory(private val repository: ShopRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailProductViewModel::class.java)) {
            return DetailProductViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
            return CartViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}