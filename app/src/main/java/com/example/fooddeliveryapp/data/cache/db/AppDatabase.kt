package com.example.fooddeliveryapp.data.cache.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.fooddeliveryapp.data.cache.models.CategoryCache
import com.example.fooddeliveryapp.data.cache.models.FoodCache
import com.example.fooddeliveryapp.data.cache.models.FoodStorageCache
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

@Database(
    entities = [CategoryCache::class, FoodStorageCache::class, FoodCache::class],
    version = 1,
    exportSchema = true
)

@TypeConverters(AppDatabase.DatabaseConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun categoriesDao(): CategoriesDao

    abstract fun storageDao(): FoodStorageDao

    abstract fun foodDao(): FoodDao


    class DatabaseConverter {
        @TypeConverter
        fun fromList(countryLang: List<String?>?): String? {
            if (countryLang == null) {
                return null
            }
            val gson = Gson()
            val type: Type = object : TypeToken<List<String?>?>() {}.type
            return gson.toJson(countryLang, type)
        }

        @TypeConverter
        fun toList(countryLangString: String?): List<String>? {
            if (countryLangString == null) {
                return null
            }
            val gson = Gson()
            val type: Type = object : TypeToken<List<String?>?>() {}.type
            return gson.fromJson<List<String>>(countryLangString, type)
        }

        @TypeConverter
        fun toDate(dateLong: Long?): Date? {
            return dateLong?.let { Date(it) }
        }

        @TypeConverter
        fun fromDate(date: Date?): Long? {
            return date?.time
        }
    }
}