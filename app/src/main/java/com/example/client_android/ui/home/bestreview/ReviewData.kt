package com.example.client_android.ui.home.bestreview

data class ReviewData(
    val placeName: String,
    val distance: Int, // 7km

    //  textView tv_kind_location에 들어갈 data 2개
    val kind: String,
    val location: String,
    val numOfStars: Float, // 각 recyclerView item에 넣어줄 star의 개수
    val averageScore: Double,
    val placeImage: Int, // imageView에 넣을 데이터는 어떤 형식? R.drawable.snow
    val review: String,
    val timeAge: Int // 46초전

)
