package com.example.client_android.network.model

data class ResponseHomeData(
    val status: Int,
    val success: Boolean,
    val data : List<Data>
){
    data class Data(
        val id : Int,
        val titleImage : String,
        val name : String,
        val rating: Float,
        val reviewCount : Int,
        val groupType : String,
        val location : String,
        val reserveFlag : Boolean,
        val lineupFlag : Boolean
    )
}
