package com.example.fooddeliveryapp.data.cache.mappers

import com.example.fooddeliveryapp.data.cache.models.FoodStorageCache
import com.example.fooddeliveryapp.data.data.models.FoodData
import com.example.fooddeliveryapp.domain.Mapper

class MapFromFoodDataToFoodStorageCache : Mapper<FoodData, FoodStorageCache> {
    override fun map(from: FoodData) = from.run {
        FoodStorageCache(
            id = foodId,
            title = name,
            price = price,
            weight = weight,
            description = description,
            tags = tags,
            image_url = image_url
        )
    }
}