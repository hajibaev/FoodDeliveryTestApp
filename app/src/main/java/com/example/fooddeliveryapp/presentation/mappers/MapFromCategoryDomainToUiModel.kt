package com.example.fooddeliveryapp.presentation.mappers

import com.example.fooddeliveryapp.domain.Mapper
import com.example.fooddeliveryapp.domain.models.CategoryDomain
import com.example.fooddeliveryapp.presentation.models.CategoryUi

class MapFromCategoryDomainToUiModel : Mapper<CategoryDomain, CategoryUi> {
    override fun map(from: CategoryDomain) = from.run {
        CategoryUi(
            id = id,
            title = title,
            image = image
        )
    }
}