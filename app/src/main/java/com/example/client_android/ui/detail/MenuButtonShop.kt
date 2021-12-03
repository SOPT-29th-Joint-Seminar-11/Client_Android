package com.example.client_android.ui.detail

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.client_android.R
import com.example.client_android.databinding.MenuButtonShopBinding
import com.example.client_android.network.model.ResponseLike
import com.example.client_android.network.model.ResponseReserve
import com.example.client_android.network.service.ServiceCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MenuButtonShop @JvmOverloads constructor(
    context: Context, attrs: AttributeSet?, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    private var binding: MenuButtonShopBinding
    // wish (하트) 개수
    private var wishes = 0
    // wish가 선택되었는지
    private var isWished = false

    private var cafeId = 1

    init {
        binding = MenuButtonShopBinding.inflate(LayoutInflater.from(context), this, true)

        initListener()
    }

    // 서버에서 받은 데이터로 wishes, isWished 초기화
    fun initData(cafeId: Int, wishes: Int, isWished: Boolean) {
        this.cafeId = cafeId
        this.wishes = wishes
        this.isWished = isWished

        initWishes()
    }

    private fun initWishes() {
        showWishBtn(isWished)
        binding.tvShopLikes.text = wishes.toString()
    }

    private fun initListener() {
        binding.clBtnShopMap.setOnClickListener {

        }
        binding.clBtnShopCall.setOnClickListener {

        }
        binding.clBtnShopShare.setOnClickListener {

        }
        binding.clBtnShopWish.setOnClickListener {

            val call: Call<ResponseLike> = ServiceCreator.cafeService.postLike(cafeId)

            call.enqueue(object: Callback<ResponseLike> {
                override fun onResponse(
                    call: Call<ResponseLike>,
                    response: Response<ResponseLike>
                ) {
                }
                override fun onFailure(call: Call<ResponseLike>, t: Throwable) {
                    Log.e("Network Error", "error: $t")
                }
            })

            binding.tvShopLikes.text = wishes.toString()
            showWishBtn(isWished)
        }
    }

    private fun showWishBtn(isWished: Boolean) {
        if(isWished) binding.ivBtnWish.setImageResource(R.drawable.ic_btn_wish_fill)
        else binding.ivBtnWish.setImageResource(R.drawable.ic_btn_wish_empty)
    }



}
