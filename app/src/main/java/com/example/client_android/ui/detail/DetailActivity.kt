package com.example.client_android.ui.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.example.client_android.databinding.ActivityDetailBinding
import com.example.client_android.network.model.ResponseReserve
import com.example.client_android.network.service.ServiceCreator
import com.google.android.material.tabs.TabLayoutMediator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var imageSliderAdapter: ImageSliderAdapter
    private lateinit var detailTabViewPagerAdapter: DetailTabViewPagerAdapter

    // cafeId : DetailView를 시작하는 Intent를 넘겨줄 때 함께 받아야 함
    private var cafeId = 1L

    // 현재 대기팀 수, 서버에서 받아올 예정
    private var waitings = 3
    // 별점, 서버에서 받아올 예정
    private var rating = 4.8f
    // 리뷰 수, 서버에서 받아올 예정
    private var reviews = 7
    // 가게와의 거리, 서버에서 받아올 예정
    private var distance = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)

        // Intent에서 cafeId 추출
        cafeId = intent.getLongExtra("cafeId", 1L)

        initListener()

        initTabAdapter()
        initTabLayout()

        initViewArrange()

        initSliderImgAdapter()
        initTextData()

        setContentView(binding.root)
    }

    private fun makeReservation() {
        val call: Call<ResponseReserve> = ServiceCreator.reserveService.postReserve(cafeId)

        call.enqueue(object: Callback<ResponseReserve> {
            override fun onResponse(
                call: Call<ResponseReserve>,
                response: Response<ResponseReserve>
            ) {
                val data = response.body()?.data

                // data?.flag를 활용해 처리
            }

            override fun onFailure(call: Call<ResponseReserve>, t: Throwable) {
                Log.e("Network Error", "error : $t")
            }
        })
    }

    /* init methods */
    private fun initListener() {
        // 즉시 예약 버튼
        binding.btnDirectReservation.setOnClickListener {
            makeReservation()
        }
    }

    private fun initTabAdapter() {
        val fragmentList = listOf(ShopInfoFragment(), ShopMenuFragment(), ShopReviewFragment())

        detailTabViewPagerAdapter = DetailTabViewPagerAdapter(this)
        detailTabViewPagerAdapter.fragments.addAll(fragmentList)

        binding.vpShopDetail.adapter = detailTabViewPagerAdapter
    }

    private fun initTabLayout() {
        val tabLabel = listOf("매장정보", "메뉴", "리뷰")

        TabLayoutMediator(binding.tlShopDetail, binding.vpShopDetail) { tab, position ->
            tab.text = tabLabel[position]
        }.attach()
    }

    private fun initSliderImgAdapter() {
        imageSliderAdapter = ImageSliderAdapter()

        binding.vpImg.adapter = imageSliderAdapter
        binding.vpImg.apply {
            registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    binding.tvIndicatorImg.text = "${position+1} / ${imageSliderAdapter.itemCount}"
                }
            })
        }

        imageSliderAdapter.notifyDataSetChanged()
    }

    private fun initTextData() {
        // 서버에서 받아와야 하는 값들
        imageSliderAdapter.dataList.addAll(
            mutableListOf("https://user-images.githubusercontent.com/37872134/141724346-6d2d0fe0-172e-47c7-bd45-6f402b01878d.png",
                "https://user-images.githubusercontent.com/37872134/141724346-6d2d0fe0-172e-47c7-bd45-6f402b01878d.png",
                "https://user-images.githubusercontent.com/37872134/141724346-6d2d0fe0-172e-47c7-bd45-6f402b01878d.png")
        )

        with(binding) {
            // 계속 사용하면 되는 초기화식 ( 위의 dataset이 들어간 다음 초기화 하는 중)
            tvIndicatorImg.text = "1 / ${imageSliderAdapter.itemCount}"

            // 서버에서 받아와야 하는 값들
            tvWaitCount.text = "대기 ${waitings}팀"
            tvShopName.text = "유니유니"
            tvShopAddress.text = "서울특별시 성북구 길음동 1276"

            llRatingBar.setStar(rating)

            tvShopDistance.text = "${distance}km"
            tvShopRating.text = "${rating}"
            tvShopReviews.text = "(${reviews})"
            tvShopIntroduction.text = "유니유니는 다양한 스콘, 쿠키, 음료, 자체 제작 케이크로 보는 즐거움과 더불어 먹는 즐거움을 선사합니다"
        }

    }

    private fun initViewArrange() {
        // viewPager에 가려지는 버튼을 더 앞으로 가져옴
        binding.tvIndicatorImg.bringToFront()
        binding.tvWaitCount.bringToFront()
    }

}