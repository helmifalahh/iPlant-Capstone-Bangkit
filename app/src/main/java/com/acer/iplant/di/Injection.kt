package com.acer.iplant.di

import com.acer.iplant.ui.shopcompose.data.ShopRepository

object Injection {
    fun provideRepository(): ShopRepository {
        return ShopRepository.getInstance()
    }
}