package com.example.fooddeliveryapp.presentation.di

import androidx.room.Room
import com.example.fooddeliveryapp.data.cache.db.AppDatabase
import org.koin.dsl.module

const val APPLICATION_DATABASE_NAME = "application_database"


val roomModule = module {

    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, APPLICATION_DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<AppDatabase>().categoriesDao() }

    single { get<AppDatabase>().storageDao() }

    single { get<AppDatabase>().foodDao() }

}