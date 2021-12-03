package com.example.client_android.network.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {
    private const val BASE_URL = "https://asia-northeast3-joint-seminar-tabling.cloudfunctions.net/api/"

    private val retrofit: Retrofit = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val cafeService: CafeService = retrofit.create(CafeService::class.java)
}