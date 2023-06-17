package com.example.fooddeliveryapp.presentation.ui

import android.app.Application
import com.example.fooddeliveryapp.presentation.di.appModule
import com.example.fooddeliveryapp.presentation.di.dataSourceModule
import com.example.fooddeliveryapp.presentation.di.interactorModule
import com.example.fooddeliveryapp.presentation.di.mapperModule
import com.example.fooddeliveryapp.presentation.di.repositoryModule
import com.example.fooddeliveryapp.presentation.di.retrofitModule
import com.example.fooddeliveryapp.presentation.di.roomModule
import com.example.fooddeliveryapp.presentation.di.routerModule
import com.example.fooddeliveryapp.presentation.di.viewModelModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        instance = this

        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    appModule,
                    retrofitModule,
                    dataSourceModule,
                    repositoryModule,
                    roomModule,
                    viewModelModel,
                    mapperModule,
                    interactorModule,
                    routerModule
                )
            )
        }
    }

    companion object {
        lateinit var instance: App
            private set
    }
}