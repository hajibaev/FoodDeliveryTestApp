package com.example.fooddeliveryapp.domain

interface Mapper<From, To> {

    fun map(from: From): To
}