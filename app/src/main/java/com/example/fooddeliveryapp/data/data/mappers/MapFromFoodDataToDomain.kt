package com.example.fooddeliveryapp.data.data.mappers

import com.example.fooddeliveryapp.data.data.models.FoodData
import com.example.fooddeliveryapp.domain.Mapper
import com.example.fooddeliveryapp.domain.models.FoodDomain

class MapFromFoodDataToDomain : Mapper<FoodData, FoodDomain> {
    override fun map(from: FoodData) = from.run {
        FoodDomain(
            foodId = foodId,
            title = name,
            price = price,
            weight = weight,
            description = description,
            image_url = image_url,
            tags = tags
        )
    }
}