package com.example.fooddeliveryapp.data.cloud.source.categories

import com.example.fooddeliveryapp.data.data.models.CategoryData
import kotlinx.coroutines.flow.Flow

interface CategoryDataSource {

    fun fetchCategories(): Flow<List<CategoryData>>
}