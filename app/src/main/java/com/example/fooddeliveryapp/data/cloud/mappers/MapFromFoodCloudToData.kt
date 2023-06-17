package com.example.fooddeliveryapp.data.cloud.mappers

import com.example.fooddeliveryapp.data.cloud.models.FoodCloud
import com.example.fooddeliveryapp.data.data.models.FoodData
import com.example.fooddeliveryapp.domain.Mapper

class MapFromFoodCloudToData : Mapper<FoodCloud, FoodData> {
    override fun map(from: FoodCloud) = from.run {
        FoodData(
            foodId = foodId,
            name = name,
            price = price,
            weight = weight,
            description = description,
            image_url = image_url,
            tags = tags
        )
    }
}