package com.example.client_android.ui.home.recommendplace

data class PlaceData(
    val placeImage: Int,
    val placeName: String,
    val averageScore: Float, // 평점 => ex) 5.0  averageScore가 2.5점 이상이면 꽉 차있는 별, 아니면 비어있는 별 이미지 보여주기

    val reviewCnt: Int, // 리뷰 개수 ex) (7)
    val kind: String,
    val location: String,

    // 즉시예약, 원격줄서기 가능한지
    val fastReservation: Boolean,
    val remoteWaiting: Boolean
)
