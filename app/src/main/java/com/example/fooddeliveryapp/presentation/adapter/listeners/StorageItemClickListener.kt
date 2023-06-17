package com.example.fooddeliveryapp.presentation.adapter.listeners

interface StorageItemClickListener {

    fun observePrice(totalPrice: Int, isPlus: Boolean)

    fun deleteFood(id: Int)

}