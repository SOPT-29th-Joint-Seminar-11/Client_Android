package com.example.client_android.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.client_android.R
import com.example.client_android.databinding.FragmentHomeBinding
import com.example.client_android.ui.home.bestreview.BestReviewAdapter
import com.example.client_android.ui.home.bestreview.ReviewData
import com.example.client_android.ui.home.recommendplace.PlaceData
import com.example.client_android.ui.home.recommendplace.RecommendPlaceAdapter


class HomeFragment : Fragment() {

    // binding
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding ?: error("Binding 이 초기화 되지 않았습니다.")

    // Adapter for RecyclerView
    private lateinit var bestReviewAdapter: BestReviewAdapter
    private lateinit var recommendPlaceAdapter: RecommendPlaceAdapter
    private lateinit var hotPlaceAdapter: RecommendPlaceAdapter

    /* 서버에서 받아올 변수들 - 통신하면서 값이 바뀔 수 있으니 var 로 선언? */
    // 현재 위치의 주소 => 서버에서 받아옴
    private var currentLocation: String = "마포구 연남동"
    // 추천카페의 종류 => 현재는 디저트에 대한 추천
    private var recommendPlace: String = "디저트가 맛있는 카페"


    private var bannerTitle: String = "분위기 깡패 루프탑, 테라스 맛집!"
    private var bannerDetail: String = "맛과 멋을 즐길 수 있는 곳"



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        initView()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun initView(){
        // 각 View들의 text에 들어갈 값이나 헤더의 주소 같은 정보들 서버에서 받거나 직접 초기화해주는 함수
        initTextView()

        // initAdapter for recyclerView
        initRecommendPlaceAdapter()
        initBestReviewAdapter()
        initHotPlaceAdapter()
    }


    /* initTextView */
    private fun initTextView(){
        with(binding)  {
            tvRecommendPlace.text = recommendPlace
            // 광고 배너 초기화
        }
    }



    /* initAdapter */

    // rv_recommend_what 의 Adapter 초기화
    private fun initRecommendPlaceAdapter(){
        recommendPlaceAdapter = RecommendPlaceAdapter()
        binding.rvRecommendWhat.adapter = recommendPlaceAdapter
        binding.rvRecommendWhat.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)

        // recyclerView 의 item 간 여백 설정하기
        binding.rvRecommendWhat.addItemDecoration(HorizontalItemDecorator(8)) // 픽셀 단위인가 dp 단위인가?

        recommendPlaceAdapter.placeList.addAll( // recyclerView 에 dummy data 넣기 => 나중에 서버에서 받아올것
            mutableListOf(
                PlaceData(R.drawable.img_uni,"유니유니", 5.0f, 7, "카페", "상수", fastReservation = true, remoteWaiting = true),
                PlaceData(R.drawable.img_uni,"카페 모이아", 4.0f, 10, "카페", "연남", fastReservation = true, remoteWaiting = false),
                PlaceData(R.drawable.img_uni,"레이어드", 2.0f, 5, "카페", "동암", fastReservation = false, remoteWaiting = true),
                PlaceData(R.drawable.img_uni,"홍대마카롱", 4.5f, 8, "카페", "신림", fastReservation = false, remoteWaiting = false),
            )
        )

        recommendPlaceAdapter.notifyDataSetChanged()
    }

    // rv_best_review 의 Adapter 초기화
    private fun initBestReviewAdapter(){
        bestReviewAdapter = BestReviewAdapter()
        binding.rvBestReview.adapter = bestReviewAdapter
        binding.rvBestReview.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)

        // recyclerView 의 item 간 여백 설정하기
        binding.rvBestReview.addItemDecoration(HorizontalItemDecorator(8)) // 픽셀 단위인가 dp 단위인가?

        bestReviewAdapter.reviewList.addAll(
            // recyclerView 에 dummy data 넣기 => 나중에 서버에서 받아올것
            mutableListOf(
                // 이 ReviewData 하나하나를 나중에 서버에서 받아올 것
                ReviewData("이태리집", 1, "이탈리안", "김포", 4.0f, 4.0, R.drawable.img_italy ,
                    """다음날도 생각나는 맛, 
                        |모든 요리가 다 괜찮았습니다.""".trimMargin(), 46), // \u0021,
                ReviewData("이태리집", 1, "이탈리안", "김포", 4.0f, 4.0, R.drawable.img_italy ,
                    """다음날도 생각나는 맛, 
                        |모든 요리가 다 괜찮았습니다.""".trimMargin(), 46),
                ReviewData("이태리집", 1, "이탈리안", "김포", 4.0f,4.0, R.drawable.img_italy ,
                    """다음날도 생각나는 맛, 
                        |모든 요리가 다 괜찮았습니다.""".trimMargin(), 46),
                ReviewData("이태리집", 1, "이탈리안", "김포", 4.0f,4.0, R.drawable.img_italy ,
                    """다음날도 생각나는 맛, 
                        |모든 요리가 다 괜찮았습니다.""".trimMargin(), 46)
            )
        )
        bestReviewAdapter.notifyDataSetChanged()
    }

    // rv_recommend_hot_place 의 adapter 초기화
    /* rv_recommend_what 와 비슷하게, RecommendPlaceAdapter 클래스를 재사용하지만 객체 자체는 다름 */
    private fun initHotPlaceAdapter(){
        hotPlaceAdapter = RecommendPlaceAdapter()
        binding.rvRecommendHotPlace.adapter = hotPlaceAdapter
        binding.rvRecommendHotPlace.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)

        // recyclerView 의 item 간 여백 설정하기
        binding.rvRecommendHotPlace.addItemDecoration(HorizontalItemDecorator(8)) // 픽셀 단위인가 dp 단위인가?

        hotPlaceAdapter.placeList.addAll( // recyclerView 에 dummy data 넣기 => 나중에 서버에서 받아올것
            mutableListOf(
                PlaceData(R.drawable.img_uni,"에드에그", 4.9f, 9, "버거", "마곡", "false".toBoolean(), "true".toBoolean()),
                PlaceData(R.drawable.img_uni,"예담밥상", 5.0f, 3, "한식", "화양", "false".toBoolean(), "true".toBoolean()),
                PlaceData(R.drawable.img_uni,"리틀넥 연남", 4.8f, 2, "카페", "연남", "false".toBoolean(), "false".toBoolean()),
                PlaceData(R.drawable.img_uni,"우동 카덴", 5.0f, 1, "카페", "동암", "true".toBoolean(), "true".toBoolean()),
            )
        )

        hotPlaceAdapter.notifyDataSetChanged()
    }

}