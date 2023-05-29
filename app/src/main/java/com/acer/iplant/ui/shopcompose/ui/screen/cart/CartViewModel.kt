package com.acer.iplant.ui.shopcompose.ui.screen.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acer.iplant.ui.shopcompose.data.ShopRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.acer.iplant.ui.shopcompose.ui.common.UiState

class CartViewModel(
    private val shoesRepository: ShopRepository
): ViewModel() {
    private val _result: MutableStateFlow<UiState<CartState>> = MutableStateFlow(UiState.Loading)
    val result: StateFlow<UiState<CartState>>
        get() = _result

    fun getAddedOrderShoes() {
        viewModelScope.launch {
            _result.value = UiState.Loading
            shoesRepository.getAddedOrderShoes()
                .collect { orderReward ->
                    val totalRequiredPoint =
                        orderReward.sumOf { it.product.price * it.count }
                    _result.value = UiState.Success(CartState(orderReward, totalRequiredPoint))
                }
        }
    }

    fun updateOrderShoes(accountId: Long, count: Int) {
        viewModelScope.launch {
            shoesRepository.updateOrderShoes(accountId, count)
                .collect { isUpdated ->
                    if (isUpdated) {
                        getAddedOrderShoes()
                    }
                }
        }
    }
}