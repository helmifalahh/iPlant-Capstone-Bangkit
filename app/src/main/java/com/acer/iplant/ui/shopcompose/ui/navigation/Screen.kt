package com.acer.iplant.ui.shopcompose.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Cart : Screen("cart")
    object Profile : Screen("profile")
    object DetailShoes : Screen("home/{accountId}") {
        fun createRoute(rewardId: Long) = "home/$rewardId"
    }
}