package com.acer.iplant.ui.shopcompose.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acer.iplant.ui.shopcompose.data.ShopRepository
import com.acer.iplant.ui.shopcompose.model.Order
import com.acer.iplant.ui.shopcompose.model.Product
import com.acer.iplant.ui.shopcompose.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val shopRepository: ShopRepository
): ViewModel() {
    private val _result: MutableStateFlow<UiState<List<Order>>> = MutableStateFlow(UiState.Loading)
    val result: StateFlow<UiState<List<Order>>>
        get() = _result

    fun getAllShoes() {
        viewModelScope.launch {
            shopRepository.getAllShoes()
                .catch {
                    _result.value = UiState.Error(it.message.toString())
                }
                .collect { orderShoes ->
                    _result.value = UiState.Success(orderShoes)
                }
        }
    }

    private val _resultCare: MutableStateFlow<UiState<List<Product>>> = MutableStateFlow(UiState.Loading)
    val resultCare: StateFlow<UiState<List<Product>>>
        get() = _resultCare

    fun getAllCare() {
        viewModelScope.launch {
            shopRepository.getAllFav()
                .catch {
                    _resultCare.value = UiState.Error(it.message.toString())
                }
                .collect { orderCare ->
                    _resultCare.value = UiState.Success(orderCare)
                }
        }
    }

    private val _resultCarousel: MutableStateFlow<UiState<List<Int>>> = MutableStateFlow(UiState.Loading)
    val resultCarousel: StateFlow<UiState<List<Int>>>
        get() = _resultCarousel

    fun getAllCarousel() {
        viewModelScope.launch {
            shopRepository.getAllCarousel()
                .catch {
                    _resultCarousel.value = UiState.Error(it.message.toString())
                }
                .collect { items ->
                    _resultCarousel.value = UiState.Success(items)
                }
        }
    }
}