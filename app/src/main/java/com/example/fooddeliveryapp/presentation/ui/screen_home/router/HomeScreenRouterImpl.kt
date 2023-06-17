package com.example.fooddeliveryapp.presentation.ui.screen_home.router

import com.example.fooddeliveryapp.presentation.ui.screen_home.FragmentHomeScreenDirections
import com.example.fooddeliveryapp.presentation.utils.navigation.NavCommand
import com.example.fooddeliveryapp.presentation.utils.navigation.toNavCommand

class HomeScreenRouterImpl : HomeScreenRouter {

    override fun navigateToFoodInfoFragment(foodId: Int): NavCommand =
        FragmentHomeScreenDirections.actionScreenHomeToFoodInfoFragment(foodId.toString())
            .toNavCommand()

}