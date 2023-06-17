package com.example.fooddeliveryapp.data.cache.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fooddeliveryapp.data.cache.models.FoodCache
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao {

    @Query("select * from foods_table")
    fun fetchAllDishesObservable(): Flow<MutableList<FoodCache>>

    @Query("select * from foods_table")
    suspend fun fetchAllDishesSingle(): MutableList<FoodCache>

    @Query("select * from foods_table where id == :id")
    fun fetchDishesFromId(id: Int): Flow<FoodCache>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewDishes(food: FoodCache)

    @Query("DELETE FROM foods_table")
    fun clearTable()
}