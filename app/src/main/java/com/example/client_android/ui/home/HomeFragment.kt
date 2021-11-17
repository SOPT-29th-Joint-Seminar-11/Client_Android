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


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding ?: error("Binding 이 초기화 되지 않았습니다.")
    private lateinit var bestReviewAdapter: BestReviewAdapter

    /* 서버에서 받아올 변수들 - 통신하면서 값이 바뀔 수 있으니 var로 선언? */
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
        initBestReviewAdapter()
    }


    /* initTextView */
    private fun initTextView(){
        with(binding)  {
            tvBestReview.text = recommendPlace

        }
    }



    /* initAdapter */
    private fun initRecommendPlaceAdapter(){

    }

    // bestReviewRecyclerView 의 Adapter 초기화하는 부분
    private fun initBestReviewAdapter(){
        bestReviewAdapter = BestReviewAdapter()
        binding.rvBestReview.adapter = bestReviewAdapter
        binding.rvBestReview.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)

        // recyclerView의 item간 여백 설정하기
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

    private fun initHotPlaceAdapter(){

    }

}