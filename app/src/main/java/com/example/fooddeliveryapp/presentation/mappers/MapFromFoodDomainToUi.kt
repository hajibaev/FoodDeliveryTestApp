package com.example.fooddeliveryapp.presentation.mappers

import com.example.fooddeliveryapp.domain.Mapper
import com.example.fooddeliveryapp.domain.models.FoodDomain
import com.example.fooddeliveryapp.presentation.models.FoodUi

class MapFromFoodDomainToUi : Mapper<FoodDomain, FoodUi> {
    override fun map(from: FoodDomain) = from.run {
        FoodUi(
            foodId = foodId,
            title = title,
            price = price,
            weight = weight,
            description = description,
            image_url = image_url,
            tags = tags
        )
    }
}