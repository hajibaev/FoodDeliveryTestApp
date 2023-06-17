package com.example.fooddeliveryapp.data.cache.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(tableName = "foods_table", indices = [Index("id")])
class FoodCache(
    @PrimaryKey val id: Int,
    @ColumnInfo("name") val title: String,
    @ColumnInfo("price") val price: Int,
    @ColumnInfo("weight") val weight: Int,
    @ColumnInfo("description") val description: String,
    @ColumnInfo("imageUrl") val imageUrl: String,
    @ColumnInfo("tegs") val tags: List<String>,
)