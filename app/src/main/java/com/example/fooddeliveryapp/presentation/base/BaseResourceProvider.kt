package com.example.fooddeliveryapp.presentation.base

import android.content.Context
import androidx.annotation.StringRes
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.presentation.utils.extensions.IdResourceString
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

interface BaseResourceProvider {

    fun getString(@StringRes id: Int): String

    fun fetchErrorMessage(exception: Exception): String

    fun fetchErrorMessage(exception: Throwable): String

    fun fetchIdErrorMessage(exception: Throwable): IdResourceString

    class Base(private val context: Context) :
        BaseResourceProvider {

        override fun getString(id: Int) = context.getString(id)

        override fun fetchErrorMessage(exception: Exception): String {
            return when (exception) {
                is UnknownHostException -> getString(R.string.network_error)
                is SocketTimeoutException -> getString(R.string.network_error)
                is ConnectException -> getString(R.string.network_error)
                else -> getString(R.string.generic_error)
            }
        }

        override fun fetchErrorMessage(exception: Throwable): String {
            return when (exception) {
                is UnknownHostException -> getString(R.string.network_error)
                is SocketTimeoutException -> getString(R.string.network_error)
                is ConnectException -> getString(R.string.network_error)
                else -> getString(R.string.generic_error)
            }
        }

        override fun fetchIdErrorMessage(exception: Throwable): IdResourceString =
            when (exception) {
                is UnknownHostException -> IdResourceString(R.string.network_error)
                is SocketTimeoutException -> IdResourceString(R.string.network_error)
                is ConnectException -> IdResourceString(R.string.network_error)
                else -> IdResourceString(R.string.generic_error)
            }

    }
}