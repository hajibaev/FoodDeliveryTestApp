package com.example.fooddeliveryapp.presentation.di

import com.example.fooddeliveryapp.presentation.ui.screen_home.router.HomeScreenRouter
import com.example.fooddeliveryapp.presentation.ui.screen_home.router.HomeScreenRouterImpl
import org.koin.dsl.module

val routerModule = module {

    single<HomeScreenRouter> {
        HomeScreenRouterImpl()
    }

}