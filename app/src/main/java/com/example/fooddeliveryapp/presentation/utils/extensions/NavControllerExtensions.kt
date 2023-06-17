package com.example.fooddeliveryapp.presentation.utils.extensions

import androidx.navigation.NavController
import com.example.fooddeliveryapp.presentation.utils.navigation.NavCommand

fun NavController.navigateTo(navCommand: NavCommand) {
    try {
        navigate(navCommand.resId, navCommand.args)
    } catch (e: java.lang.Exception) {
    }
}
