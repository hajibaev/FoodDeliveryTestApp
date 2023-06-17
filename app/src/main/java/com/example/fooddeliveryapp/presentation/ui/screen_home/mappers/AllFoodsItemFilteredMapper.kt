package com.example.fooddeliveryapp.presentation.ui.screen_home.mappers

import com.example.fooddeliveryapp.domain.models.FoodDomain
import com.example.fooddeliveryapp.presentation.models.FoodUi

interface AllFoodsItemFilteredMapper {

    fun map(
        items: List<FoodDomain>,
        filterTags: String,
    ): List<FoodUi>
}