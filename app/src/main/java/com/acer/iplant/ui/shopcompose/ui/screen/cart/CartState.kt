package com.acer.iplant.ui.shopcompose.ui.screen.cart

import com.acer.iplant.ui.shopcompose.model.Order

data class CartState(
    val orderReward: List<Order>,
    val totalRequiredPoint: Int
)