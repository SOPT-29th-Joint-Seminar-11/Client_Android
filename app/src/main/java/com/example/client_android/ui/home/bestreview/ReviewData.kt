package com.example.client_android.ui.home.bestreview

data class ReviewData(
    val cafeName: String,
    val distance: Int, // 7km
    val kindAndLocation: String,
    // rating bar에는 어떻게 넣어줘야 하나?
    val averageScore: Double,
    val cafeImage: Int, // imageView에 넣을 데이터는 어떤 형식? R.drawable.snow
    val review: String,
    val timeAge: Int // 46초전

)
