package com.example.fooddeliveryapp.data.cache.mappers

import com.example.fooddeliveryapp.data.cache.models.FoodCache
import com.example.fooddeliveryapp.data.data.models.FoodData
import com.example.fooddeliveryapp.domain.Mapper

class MapFromFoodCacheToData : Mapper<FoodCache, FoodData> {
    override fun map(from: FoodCache) = from.run {
        FoodData(
            foodId = id,
            name = title,
            price = price,
            weight = weight,
            description = description,
            tags = tags,
            image_url = imageUrl
        )
    }
}