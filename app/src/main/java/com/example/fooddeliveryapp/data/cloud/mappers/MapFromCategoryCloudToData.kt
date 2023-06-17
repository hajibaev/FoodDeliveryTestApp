package com.example.fooddeliveryapp.data.cloud.mappers

import com.example.fooddeliveryapp.data.cloud.models.FoodCategoryCloud
import com.example.fooddeliveryapp.data.data.models.CategoryData
import com.example.fooddeliveryapp.domain.Mapper

class MapFromCategoryCloudToData : Mapper<FoodCategoryCloud, CategoryData> {
    override fun map(from: FoodCategoryCloud) = from.run {
        CategoryData(
            id = id,
            title = title,
            image = image
        )
    }
}