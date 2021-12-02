package com.example.client_android.network.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class ResponseCafeDetail(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: Data
){
    data class Data(
        val info: InfoData,
        val detail: DetailData
    ){
        data class InfoData(
            val images: List<String>, // ArrayList<String> 인지 ? MutableList<String>? => List로
            val waitingCount: Int,
            val distance: Float,
            val name: String,
            val address: String,
            val rating: Float,
            val reviewCount: Int,
            val description: String,
            val likeFlag: Boolean,
            val likeCount: Int
        )
        @Parcelize
        data class DetailData(
            val tags: List<String>,
            val pet: Int,
            val wifi: Int,
            val parking: Int
        ) : Parcelable
    }
}








