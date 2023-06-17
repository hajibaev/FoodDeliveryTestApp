package com.example.fooddeliveryapp.data.cache.mappers

import com.example.fooddeliveryapp.data.cache.models.FoodCache
import com.example.fooddeliveryapp.data.data.models.FoodData
import com.example.fooddeliveryapp.domain.Mapper

class MapFromFoodDataToCache : Mapper<FoodData, FoodCache> {
    override fun map(from: FoodData) = from.run {
        FoodCache(
            id = foodId,
            title = name,
            price = price,
            weight = weight,
            description = description,
            tags = tags,
            imageUrl = image_url
        )
    }
}