package com.example.fooddeliveryapp.domain.repository

import com.example.fooddeliveryapp.domain.models.CategoryDomain
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    fun fetchCategories(): Flow<List<CategoryDomain>>

    suspend fun fetchCategoriesFromCache(category_id: Int): CategoryDomain


}