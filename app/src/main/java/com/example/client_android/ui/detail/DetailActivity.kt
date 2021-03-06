package com.example.client_android.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.example.client_android.R
import com.example.client_android.databinding.ActivityDetailBinding
import com.example.client_android.network.model.ResponseCafeDetail
import com.example.client_android.network.model.ResponseReserve
import com.example.client_android.network.service.ServiceCreator
import com.example.client_android.util.simpleDialog
import com.google.android.material.tabs.TabLayoutMediator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var imageSliderAdapter: ImageSliderAdapter
    private lateinit var detailTabViewPagerAdapter: DetailTabViewPagerAdapter

    // cafeId : DetailView를 시작하는 Intent를 넘겨줄 때 함께 받아야 함
    private var cafeId = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)

        cafeId = intent.getIntExtra("cafeId", 1)
        initListener()

        initTextData()  // 서버에서 카페 상세정보 받아온 후 뿌려주기기
        // initTabAdapter()
        // initTabLayout() 모두 여기서 실행해줄 것

        initViewArrange()

        initSliderImgAdapter()

        setContentView(binding.root)
    }

    private fun makeReservation() {
        val call: Call<ResponseReserve> = ServiceCreator.cafeService.postReserve(cafeId)

        var msg: String
        val btn = getString(R.string.detail_dialog_btn_ok)

        call.enqueue(object: Callback<ResponseReserve> {
            override fun onResponse(
                call: Call<ResponseReserve>,
                response: Response<ResponseReserve>
            ) {
                val data = response.body()?.data

                // flag 값이 안들어왔을 경우 -> error
                if (data?.flag == null) {
                    msg = getString(R.string.detail_dialog_msg_fail)
                }
                // flag가 true일 경우 -> 예약 완료
                else if (data.flag) {
                    msg = getString(R.string.detail_dialog_msg_success_reserved)
                }
                // flag가 false일 경우 -> 예약 취소
                else {
                    msg = getString(R.string.detail_dialog_msg_success_canceled)
                }

                simpleDialog(msg, btn)
            }

            override fun onFailure(call: Call<ResponseReserve>, t: Throwable) {
                Log.e("Network Error", "error : $t")

                msg = getString(R.string.detail_dialog_msg_fail)
                simpleDialog(msg, btn)
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


    private fun initTabAdapter(tags: ResponseCafeDetail.Data.DetailData) {

        // ShopInfoFragment 로 data를 전달하기 위해 인자로 넘겨받은 서버에서 받은 data를 bundle 에 넣어서 전달한다
        val shopInfoFragment = ShopInfoFragment()
        val bundle = Bundle()
        bundle.putParcelable("tags", tags)
        shopInfoFragment.arguments = bundle

        val fragmentList = listOf(shopInfoFragment, ShopMenuFragment(), ShopReviewFragment())

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

    private fun initTextData() { // 서버에서 값을 받아와 카페 상세정보 뿌려주기
        val callCafeDetail: Call<ResponseCafeDetail> = ServiceCreator.cafeService.getCafeDetail(cafeId)

        callCafeDetail.enqueue(object: Callback<ResponseCafeDetail> {
            override fun onResponse(
                callCafeDetail: Call<ResponseCafeDetail>,
                response: Response<ResponseCafeDetail>
            ) {
                if (response.isSuccessful) { // status가 200 ~ 300 일 때,

                    // 매장정보에 대한 detail { tags, pet, wifi, parking } 정보들은 ShopInfoFragment로 전달하기 위해
                    // initTabAdapter() 메서드의 인자로 전달
                    val data = response.body()?.data

                    initTabAdapter(data?.detail!!)
                    initTabLayout()

                    // cafeImage 초기화
                    val cafeImageList = data?.info?.images

                    imageSliderAdapter.dataList.addAll(cafeImageList!!)
                    // 원래 위의 코드가 맞지만 현재, 서버 오류로 밑의 코드로 대신해서 사용중
                    imageSliderAdapter.notifyDataSetChanged()

                    // 나머지 정보들 초기화
                    with(binding){
                        tvIndicatorImg.text = "1 / ${imageSliderAdapter.itemCount}" // 이건 서버 통신 불필요 ?

                        tvWaitCount.text = "대기 ${data?.info?.waitingCount}팀" // 대기 3팀
                        tvShopName.text = data?.info?.name // 유니유니
                        tvShopDistance.text = "${data?.info?.distance}km" // 1km
                        tvShopAddress.text = data?.info?.address // 서울특별시 성북구 길음동 1276

                        llRatingBar.setStar(data?.info?.rating!!) // !! 표시 쓰는거 맞나 별 4개

                        tvShopRating.text = "${data?.info?.rating}" // 4.8
                        tvShopReviews.text = "(${data?.info?.reviewCount})" // (7)
                        tvShopIntroduction.text = "${data?.info?.description}" // 유니유니는 ~

                        // 좋아요 정보 적용
                        llMenuButtonShop.initData(cafeId, data?.info?.likeCount, data?.info?.likeFlag)
                    }

                }
                else {
                    val message = response.body()?.message
                    Toast.makeText(this@DetailActivity, "$message", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseCafeDetail>, t: Throwable) {
                Toast.makeText(this@DetailActivity, "onFailure", Toast.LENGTH_SHORT).show()
                Log.e("NetworkTest", "error:$t")
            }
        })


    }

    private fun initViewArrange() {
        // viewPager에 가려지는 버튼을 더 앞으로 가져옴
        binding.tvIndicatorImg.bringToFront()
        binding.tvWaitCount.bringToFront()
    }

}