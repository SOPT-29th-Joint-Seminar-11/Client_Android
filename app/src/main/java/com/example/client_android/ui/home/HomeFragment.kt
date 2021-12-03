package com.example.client_android.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.client_android.R
import com.example.client_android.databinding.FragmentHomeBinding
import com.example.client_android.network.model.ResponseHomeData
import com.example.client_android.network.service.ServiceCreator
import com.example.client_android.ui.detail.DetailActivity
import com.example.client_android.ui.home.bestreview.BestReviewAdapter
import com.example.client_android.ui.home.bestreview.ReviewData
import com.example.client_android.ui.home.recommendplace.HotPlaceAdapter
import com.example.client_android.ui.home.recommendplace.PlaceData
import com.example.client_android.ui.home.recommendplace.RecommendPlaceAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {

    // binding
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding ?: error("Binding 이 초기화 되지 않았습니다.")

    // Adapter for RecyclerView
    private lateinit var bestReviewAdapter: BestReviewAdapter
    private lateinit var recommendPlaceAdapter: RecommendPlaceAdapter
    private lateinit var hotPlaceAdapter: HotPlaceAdapter

    /* 서버에서 받아올 변수들 - 통신하면서 값이 바뀔 수 있으니 var 로 선언? */
    // 현재 위치의 주소 => 서버에서 받아옴
    private var currentLocation = "마포구 연남동"
    // 추천카페의 종류 => 현재는 디저트에 대한 추천
    private var recommendPlace = "디저트가 맛있는 카페"


    private var bannerTitle = "분위기 깡패 루프탑, 테라스 맛집!"
    private var bannerDetail = "맛과 멋을 즐길 수 있는 곳"



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        initView()
        stickyHeader()
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
        initNetwork()

        //setListener()
    }


    /* initTextView */
    private fun initTextView(){
        with(binding)  {
            tvRecommendPlace.text = recommendPlace
            tvBannerTitle.text = "분위기 깡패 루프탑, 테라스 맛집!"
            tvBannerDetail.text = "맛과 멋을 즐길 수 있는 곳!"
            ivBanner.setImageResource(R.drawable.img_rooftop)
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

        //클릭리스너 등록
        recommendPlaceAdapter.setItemClickListener( object : RecommendPlaceAdapter.ItemClickListener{
            override fun onClick(view: View, position: Int) {
                activity?.let{
                    val intent = Intent(context, DetailActivity::class.java)
                    intent.putExtra("cafeId", position)
                    startActivity(intent)
                }

            }
        })
    }

    private fun initNetwork(){
        val call: Call<ResponseHomeData> = ServiceCreator.reserveService.getHome()

        call.enqueue(object: Callback<ResponseHomeData> {
            override fun onResponse(
                call: Call<ResponseHomeData>,
                response: Response<ResponseHomeData>
            ) {
                if(response.isSuccessful){
                    val data = response.body()?.data
                    if(data != null) {
                        recommendPlaceAdapter.recommendplaceList.addAll(data)
                        recommendPlaceAdapter.notifyDataSetChanged()
                    }
                }else{
                    Toast.makeText(activity, "데이터 로딩에 실패했습니다.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseHomeData>, t: Throwable) {
                Log.e("Network Error", "error : $t")
            }
        })
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
        hotPlaceAdapter = HotPlaceAdapter()
        binding.rvRecommendHotPlace.adapter = hotPlaceAdapter
        binding.rvRecommendHotPlace.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)

        // recyclerView 의 item 간 여백 설정하기
        binding.rvRecommendHotPlace.addItemDecoration(HorizontalItemDecorator(8)) // 픽셀 단위인가 dp 단위인가?

        hotPlaceAdapter.placeList.addAll( // recyclerView 에 dummy data 넣기 => 나중에 서버에서 받아올것
            mutableListOf(
                PlaceData(R.drawable.img_uni,"에드에그", 4.9f, 9, "버거", "마곡", "false".toBoolean(), "true".toBoolean()),
                PlaceData(R.drawable.img_uni,"예담밥상", 5.0f, 3, "한식", "화양", "true".toBoolean(), "false".toBoolean()),
                PlaceData(R.drawable.img_uni,"리틀넥 연남", 4.8f, 2, "카페", "연남", "false".toBoolean(), "false".toBoolean()),
                PlaceData(R.drawable.img_uni,"우동 카덴", 5.0f, 1, "카페", "동암", "true".toBoolean(), "true".toBoolean()),
            )
        )

        hotPlaceAdapter.notifyDataSetChanged()
    }

    private fun stickyHeader(){
        binding.nsvHome.run{
            header = binding.clSearch
        }
    }
}