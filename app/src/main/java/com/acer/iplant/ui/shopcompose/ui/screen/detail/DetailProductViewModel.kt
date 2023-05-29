package com.acer.iplant.ui.shopcompose.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acer.iplant.ui.shopcompose.data.ShopRepository
import com.acer.iplant.ui.shopcompose.model.Order
import com.acer.iplant.ui.shopcompose.model.Product
import com.acer.iplant.ui.shopcompose.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailProductViewModel(
    private val shopRepository: ShopRepository
): ViewModel() {
    private val _result: MutableStateFlow<UiState<Order>> =
        MutableStateFlow(UiState.Loading)
    val result: StateFlow<UiState<Order>>
        get() = _result

    fun getRewardById(rewardId: Long) {
        viewModelScope.launch {
            _result.value = UiState.Loading
            _result.value = UiState.Success(shopRepository.getOrderRewardById(rewardId))
        }
    }

    fun addToCart(product: Product, count: Int) {
        viewModelScope.launch {
            shopRepository.updateOrderShoes(product.id, count)
        }
    }
}