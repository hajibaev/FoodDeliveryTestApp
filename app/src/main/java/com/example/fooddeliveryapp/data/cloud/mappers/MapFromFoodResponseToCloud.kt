package com.example.fooddeliveryapp.data.cloud.mappers

import com.example.fooddeliveryapp.data.cloud.models.FoodCloud
import com.example.fooddeliveryapp.data.cloud.models.FoodResponseCloud
import com.example.fooddeliveryapp.domain.Mapper

class MapFromFoodResponseToCloud : Mapper<FoodResponseCloud, FoodCloud> {
    override fun map(from: FoodResponseCloud): FoodCloud {
        if (from.foods.isEmpty()) {
        }

        val dishes = from.foods.first()
        return FoodCloud(
            foodId = dishes.foodId,
            name = dishes.name,
            price = dishes.price,
            weight = dishes.weight,
            description = dishes.description,
            image_url = dishes.image_url,
            tags = dishes.tags
        )
    }
}