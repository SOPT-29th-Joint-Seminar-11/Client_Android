package com.example.client_android.network.model

data class ResponseReserve (
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: Data
) {
    data class Data (
        val id: Long,
        val restaurantId: Long,
        val flag: Boolean,
        val createdAt: String,
        val updatedAt: String
        )
}
