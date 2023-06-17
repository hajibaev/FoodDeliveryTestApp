package com.example.fooddeliveryapp.presentation.models

data class FoodUi(
    val foodId: Int,
    val title: String,
    val price: Int,
    val weight: Int,
    val description: String,
    val image_url: String,
    val tags: List<String>,
    var count: Int = 1,
    var totalPrice: Int = 0
)
