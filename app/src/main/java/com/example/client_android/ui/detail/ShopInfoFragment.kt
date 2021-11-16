package com.example.client_android.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.client_android.R
import com.example.client_android.databinding.FragmentShopInfoBinding

class ShopInfoFragment : Fragment() {
    private var _binding: FragmentShopInfoBinding? = null
    private val binding get() = _binding!!
    // pick 목록, 서버에서 받아올 예정
    private val picks = mutableListOf<String>()
    // 편의시설 목록, 서버에서 받아올 예정
    private val facilities = mutableListOf<String>()
    // 운영시간, 서버에서 받아올 예정
    private var openTimeStart = "11:30"
    private var openTimeEnd = "22:00"
    // 휴식시간, 서버에서 받아올 예정
    private var breakTimeStart = "16:00"
    private var breakTimeEnd = "17:00"
    // 휴무일, 서버에서 받아올 예정
    private var holiday = "일요일"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShopInfoBinding.inflate(layoutInflater, container, false)

        initTextData()

        return binding.root
    }

    private fun initTextData() {

        // 서버에서 받아와야하는 데이터들
        binding.tvShopDetailOpenTime.text = "오늘 $openTimeStart ~ $openTimeEnd"
        binding.tvShopDetailBreakTime.text = "$breakTimeStart ~ $breakTimeEnd"
        binding.tvShopDetailHoliday.text = "$holiday"

        picks.addAll(
            listOf(
                "깔끔한", "조용한", "차분한", "데이트 하기 좋은", "디저트", "단체석"
            )
        )
        binding.llViewPick.setPicks(picks)

    }


}