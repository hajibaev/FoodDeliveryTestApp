package com.example.fooddeliveryapp.presentation.adapter.listeners

interface StorageItemClickListener {

    fun observePrice(totalPrice: Int, isPlus: Boolean, count:Int)

    fun deleteFood(id: Int)

}