package com.example.client_android.network.service

import com.example.client_android.network.model.ResponseCafeDetail
import com.example.client_android.network.model.ResponseHomeData
import com.example.client_android.network.model.ResponseReserve
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface ReserveService {
    @Headers("Content-Type: application/json")
    @POST("cafe-detail/{cafeId}/reserve")
    fun postReserve(
        @Path("cafeId") cafeId: Int
    ) : Call<ResponseReserve>

    @Headers("Content-Type: application/json")
    @GET("cafe-detail/{cafeId}")
    fun getCafeDetail(
        @Path("cafeId") cafeId: Int
    ) : Call<ResponseCafeDetail>

    @Headers("Content-Type: application/json")
    @GET("home/cafe")
    fun getHome(
    ) : Call<ResponseHomeData>
}