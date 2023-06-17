package com.example.fooddeliveryapp.presentation.di

import com.example.fooddeliveryapp.data.base.ResponseHandler
import com.example.fooddeliveryapp.data.base.ResponseHandlerImpl
import com.example.fooddeliveryapp.domain.DispatchersProvider
import com.example.fooddeliveryapp.presentation.base.BaseResourceProvider
import org.koin.dsl.module

val appModule = module {

    single<ResponseHandler> { ResponseHandlerImpl(dispatchersProvider = get()) }

    single<DispatchersProvider> { DispatchersProvider.Base() }

    single<BaseResourceProvider> { BaseResourceProvider.Base(context = get()) }
}