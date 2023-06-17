package com.example.fooddeliveryapp.data.cache.mappers

import com.example.fooddeliveryapp.data.cache.models.FoodStorageCache
import com.example.fooddeliveryapp.data.data.models.FoodData
import com.example.fooddeliveryapp.domain.Mapper

class MapFromFoodStorageCacheToData : Mapper<FoodStorageCache, FoodData> {
    override fun map(from: FoodStorageCache) = from.run {
        FoodData(
            foodId = id,
            name = title,
            price = price,
            weight = weight,
            description = description,
            tags = tags,
            image_url = image_url
        )
    }
}