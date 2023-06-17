package com.example.fooddeliveryapp.data.cloud.models

import com.google.gson.annotations.SerializedName

data class FoodCategoryResponseCloud(
    @SerializedName("сategories") val category: List<FoodCategoryCloud>
)

data class FoodCategoryCloud(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val title: String,
    @SerializedName("image_url") val image: String
)

