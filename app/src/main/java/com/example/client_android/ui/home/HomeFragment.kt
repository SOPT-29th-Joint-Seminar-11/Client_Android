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
import com.example.client_android.ui.RatingBar
import com.example.client_android.ui.home.bestreview.BestReviewAdapter
import com.example.client_android.ui.home.bestreview.ReviewData


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding ?: error("Binding 이 초기화 되지 않았습니다.")
    private lateinit var bestReviewAdapter: BestReviewAdapter
    private lateinit var bestReviewRecyclerView: RecyclerView

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
        // 각 textView의 text에 들어갈 값이나 헤더의 주소 같은 정보들 서버에서 받아서 초기화해주는 함수
        initBestReviewAdapter()
    }






    private fun initRecommendDessertAdapter(){
    }

    // bestReviewRecyclerView 의 Adapter 초기화하는 부분
    private fun initBestReviewAdapter(){
        bestReviewAdapter = BestReviewAdapter()
        binding.rvBestReview.adapter = bestReviewAdapter
        binding.rvBestReview.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)

        bestReviewAdapter.reviewList.addAll(
            // recyclerView 에 dummy data 넣기 => 나중에 서버에서 받아올것
            mutableListOf(
                ReviewData("이태리집", 1, "이탈리안 · 김포", 4.0f, 4.0, R.drawable.img_italy ,
                    """다음날도 생각나는 맛, 
                        |모든 요리가 다 괜찮았습니다""".trimMargin(), 46), // \u0021,
                ReviewData("이태리집", 1, "이탈리안 · 김포", 4.0f, 4.0, R.drawable.img_italy ,
                    """다음날도 생각나는 맛, 
                        |모든 요리가 다 괜찮았습니다""".trimMargin(), 46),
                ReviewData("이태리집", 1, "이탈리안 · 김포", 4.0f,4.0, R.drawable.img_italy ,
                    """다음날도 생각나는 맛, 
                        |모든 요리가 다 괜찮았습니다""".trimMargin(), 46),
                ReviewData("이태리집", 1, "이탈리안 · 김포", 4.0f,4.0, R.drawable.img_italy ,
                    """다음날도 생각나는 맛, 
                        |모든 요리가 다 괜찮았습니다""".trimMargin(), 46)
            )
        )
        bestReviewAdapter.notifyDataSetChanged()
    }

    private fun initHotPlaceAdapter(){

    }

}