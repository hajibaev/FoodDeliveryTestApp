package com.example.fooddeliveryapp.presentation.ui.screen_home.mappers

import com.example.fooddeliveryapp.domain.Mapper
import com.example.fooddeliveryapp.domain.models.FoodDomain
import com.example.fooddeliveryapp.presentation.adapter.listeners.FoodItemClickListener
import com.example.fooddeliveryapp.presentation.models.FoodUi

class AllFoodsItemFilteredMapperImpl(
    private val mapFromDishesDomainToUi: Mapper<FoodDomain, FoodUi>,
) : AllFoodsItemFilteredMapper {

    override fun map(
        items: List<FoodDomain>,
        filterTags: String,
    ): List<FoodUi> {

        val allFilteredDishesList = items.map(mapFromDishesDomainToUi::map)

        val filteredDishesItem = allFilteredDishesList.filter {
            applyAllFilteredFoods(it, tegs = filterTags)
        }

        return filteredDishesItem
    }


    private fun applyAllFilteredFoods(food: FoodUi, tegs: String) =
        if (tegs.isEmpty()) food.tags.toString() != String()
        else food.tags.toString().contains(tegs, ignoreCase = true)

}