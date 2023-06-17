package com.example.fooddeliveryapp.data.data.mappers

import com.example.fooddeliveryapp.data.data.models.FoodData
import com.example.fooddeliveryapp.domain.Mapper
import com.example.fooddeliveryapp.domain.models.FoodDomain

class MapFromFoodDomainToData : Mapper<FoodDomain, FoodData> {
    override fun map(from: FoodDomain) = from.run {
        FoodData(
            foodId = foodId,
            name = title,
            price = price,
            weight = weight,
            description = description,
            image_url = image_url,
            tags = tags
        )
    }
}