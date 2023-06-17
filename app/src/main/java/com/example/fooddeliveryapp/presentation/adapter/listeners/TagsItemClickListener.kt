package com.example.fooddeliveryapp.presentation.adapter.listeners

interface TagsItemClickListener {
    fun itemClickListener(tag: String, previousItem: Int, selectedItem: Int)
}