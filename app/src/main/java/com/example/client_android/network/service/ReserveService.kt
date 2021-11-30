package com.example.client_android.network.service

import retrofit2.Call
import retrofit2.http.Headers
import retrofit2.http.POST

interface ReserveService {
    @Headers("Content-Type:application/json")
    @POST("cafe-detail/{cafeId}/reserve")
    fun postReserve(

    ) : Call<ResponseReserve>
}