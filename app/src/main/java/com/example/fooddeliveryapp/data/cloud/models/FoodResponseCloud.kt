package com.example.fooddeliveryapp.data.cloud.models

import com.google.gson.annotations.SerializedName


data class FoodResponseCloud(
    @SerializedName("dishes") val foods: List<FoodCloud>
)

data class FoodCloud(
    @SerializedName("id") val foodId: Int,
    @SerializedName("name") val name: String,
    @SerializedName("price") val price: Int,
    @SerializedName("weight") val weight: Int,
    @SerializedName("description") val description: String,
    @SerializedName("image_url") val image_url: String,
    @SerializedName("tegs") val tags: List<String>,
)
