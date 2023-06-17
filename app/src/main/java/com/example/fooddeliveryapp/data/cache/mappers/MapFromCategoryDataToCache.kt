package com.example.fooddeliveryapp.data.cache.mappers

import com.example.fooddeliveryapp.data.cache.models.CategoryCache
import com.example.fooddeliveryapp.data.data.models.CategoryData
import com.example.fooddeliveryapp.domain.Mapper

class MapFromCategoryDataToCache : Mapper<CategoryData, CategoryCache> {
    override fun map(from: CategoryData) = from.run {
        CategoryCache(
            id = id,
            title = title,
            image = image
        )
    }
}