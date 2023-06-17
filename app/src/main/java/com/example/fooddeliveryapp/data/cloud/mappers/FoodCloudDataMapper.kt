package com.example.fooddeliveryapp.data.cloud.mappers

import com.example.fooddeliveryapp.data.cloud.models.FoodCloud
import com.example.fooddeliveryapp.data.data.models.FoodData
import com.example.fooddeliveryapp.domain.Mapper

interface FoodCloudDataMapper {

    fun map(): Mapper<FoodCloud, FoodData>
}

class FoodCloudDataMapperImpl : FoodCloudDataMapper {

    override fun map() = object : Mapper<FoodCloud, FoodData> {
        override fun map(from: FoodCloud): FoodData = createFoodsData(from)
    }

    private fun createFoodsData(foodCloud: FoodCloud) = foodCloud.run {
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