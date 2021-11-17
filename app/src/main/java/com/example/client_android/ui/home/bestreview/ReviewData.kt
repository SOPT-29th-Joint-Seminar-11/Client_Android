package com.example.client_android.ui.home.bestreview

data class ReviewData(
    val cafeName: String,
    val distance: Int, // 7km
    val kindAndLocation: String,
    val numOfStars: Float, // 각 recyclerView item에 넣어줄 star의 개수
    val averageScore: Double,
    val cafeImage: Int, // imageView에 넣을 데이터는 어떤 형식? R.drawable.snow
    val review: String,
    val timeAge: Int // 46초전

)
