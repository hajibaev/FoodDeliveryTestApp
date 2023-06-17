package com.example.fooddeliveryapp.data.cache.mappers

import com.example.fooddeliveryapp.data.cache.models.CategoryCache
import com.example.fooddeliveryapp.data.data.models.CategoryData
import com.example.fooddeliveryapp.domain.Mapper

class MapFromCategoryCacheToData : Mapper<CategoryCache, CategoryData> {
    override fun map(from: CategoryCache) = from.run {
        CategoryData(
            id = id,
            title = title,
            image = image
        )
    }
}