package com.example.fooddeliveryapp.data.data.mappers

import com.example.fooddeliveryapp.data.data.models.CategoryData
import com.example.fooddeliveryapp.domain.Mapper
import com.example.fooddeliveryapp.domain.models.CategoryDomain

class CategoryDataToDomainMapper : Mapper<CategoryData, CategoryDomain> {
    override fun map(from: CategoryData) = from.run {
        CategoryDomain(
            id = id,
            title = title,
            image = image
        )
    }
}