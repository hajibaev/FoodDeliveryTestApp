package com.example.fooddeliveryapp.domain.models

data class FoodDomain(
    val foodId: Int,
    val title: String,
    val price: Int,
    val weight: Int,
    val description: String,
    val image_url: String,
    val tags: List<String>,
)
