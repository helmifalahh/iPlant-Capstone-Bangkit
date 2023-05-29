package com.acer.iplant.ui.shopcompose.data

import com.acer.iplant.ui.shopcompose.model.Order
import com.acer.iplant.ui.shopcompose.model.Product
import com.acer.iplant.ui.shopcompose.model.ProductData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class ShopRepository {

    private val orderProduct = mutableListOf<Order>()
    private val orderFav = mutableListOf<Product>()
    private val orderCarousel = mutableListOf<Int>()

    init {
        if (orderCarousel.isEmpty()) {
            ProductData.imageCarousel().forEach {
                orderCarousel.add(it)
            }
        }
        if (orderProduct.isEmpty()) {
            ProductData.product.forEach {
                orderProduct.add(Order(it, 0))
            }
        }
        if (orderFav.isEmpty()) {
            ProductData.productFavorite.forEach {
                orderFav.add(it)
            }
        }
    }

    fun getAllShoes(): Flow<List<Order>> {
        return flowOf(orderProduct)
    }

    fun getOrderRewardById(rewardId: Long): Order {
        return orderProduct.first {
            it.product.id == rewardId
        }
    }

    fun updateOrderShoes(accountId: Long, newCountValue: Int): Flow<Boolean> {
        val index = orderProduct.indexOfFirst { it.product.id == accountId }
        val result = if (index >= 0) {
            val orderReward = orderProduct[index]
            orderProduct[index] =
                orderReward.copy(product = orderReward.product, count = newCountValue)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    fun getAddedOrderShoes(): Flow<List<Order>> {
        return getAllShoes()
            .map { orderRewards ->
                orderRewards.filter { orderReward ->
                    orderReward.count != 0
                }
            }
    }

    fun getAllFav(): Flow<List<Product>> {
        return flowOf(orderFav)
    }

    fun getAllCarousel(): Flow<List<Int>> {
        return flowOf(orderCarousel)
    }

    companion object {
        @Volatile
        private var instance: ShopRepository? = null

        fun getInstance(): ShopRepository =
            instance ?: synchronized(this) {
                ShopRepository().apply {
                    instance = this
                }
            }
    }
}