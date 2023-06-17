package com.example.fooddeliveryapp.presentation.ui.screen_home.router

import com.example.fooddeliveryapp.presentation.utils.navigation.NavCommand


interface HomeScreenRouter {

    fun navigateToFoodInfoFragment(foodId: Int): NavCommand
}