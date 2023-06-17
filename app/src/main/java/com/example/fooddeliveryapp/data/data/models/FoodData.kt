package com.example.fooddeliveryapp.data.data.models

class FoodData(
    val foodId: Int,
    val name: String,
    val price: Int,
    val weight: Int,
    val description: String,
    val image_url: String,
    val tags: List<String>,
) {

    companion object {
        fun unknown() = FoodData(
            foodId = String().toInt(),
            name = String(),
            price = String().toInt(),
            weight = String().toInt(),
            description = String(),
            image_url = String(),
            tags = listOf(String())
        )
    }

}