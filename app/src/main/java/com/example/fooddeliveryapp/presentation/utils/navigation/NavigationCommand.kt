package com.example.fooddeliveryapp.presentation.utils.navigation

import androidx.navigation.NavDirections

sealed class NavigationCommand {

    data class ToDirection(val directions: NavDirections) : NavigationCommand()

    object Back : NavigationCommand()
}