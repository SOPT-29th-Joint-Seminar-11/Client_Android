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
    private lateinit var bestReviewRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        initBestReviewAdapter()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    // bestReviewRecyclerView 의 Adapter 초기화하는 부분
    private fun initBestReviewAdapter(){
        bestReviewAdapter = BestReviewAdapter()
        binding.rvBestReview.adapter = bestReviewAdapter
        binding.rvBestReview.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)

        bestReviewAdapter.reviewList.addAll( // recyclerView 에 dummy data 넣기
            listOf(
                ReviewData("이태리집", 1, "이탈리안 · 김포", 4.0, R.drawable.ic_launcher_foreground ,
                    "다음날도 생각나는 맛, 모든 요리가 다 괜찮았습니다", 46), // \u0021,
                ReviewData("이태리집", 1, "이탈리안 · 김포", 4.0, R.drawable.ic_launcher_foreground  ,
                    "다음날도 생각나는 맛, 모든 요리가 다 괜찮았습니다", 46),
                ReviewData("이태리집", 1, "이탈리안 · 김포", 4.0, R.drawable.ic_launcher_foreground  ,
                    "다음날도 생각나는 맛, 모든 요리가 다 괜찮았습니다", 46),
                ReviewData("이태리집", 1, "이탈리안 · 김포", 4.0, R.drawable.ic_launcher_foreground  ,
                    "다음날도 생각나는 맛, 모든 요리가 다 괜찮았습니다", 46)
            )
        )

        bestReviewAdapter.notifyDataSetChanged()
    }

}