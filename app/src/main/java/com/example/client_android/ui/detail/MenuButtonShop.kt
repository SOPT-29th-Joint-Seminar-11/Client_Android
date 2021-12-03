package com.example.client_android.ui.detail

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.Toast
import com.example.client_android.R
import com.example.client_android.databinding.MenuButtonShopBinding
import com.example.client_android.network.model.ResponseCafeDetail
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

        refresh()
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

            loadWishDataFromNetwork()
        }
    }

    private fun refresh() {
        binding.tvShopLikes.text = wishes.toString()
        showWishBtn(isWished)
    }

    private fun refresh(likes: Int, isLiked: Boolean) {
        binding.tvShopLikes.text = likes.toString()
        showWishBtn(isLiked)
    }

    private fun loadWishDataFromNetwork() {

        val call: Call<ResponseCafeDetail> = ServiceCreator.cafeService.getCafeDetail(cafeId)
        call.enqueue(object: Callback<ResponseCafeDetail> {
            override fun onResponse(
                call: Call<ResponseCafeDetail>,
                response: Response<ResponseCafeDetail>
            ) {
                val info = response.body()?.data?.info

                wishes = info?.likeCount!!
                isWished = info?.likeFlag!!
                refresh(info?.likeCount, info?.likeFlag)
            }

            override fun onFailure(call: Call<ResponseCafeDetail>, t: Throwable) {
                Log.e("NetworkTest", "error:$t")
            }
        })
    }

    private fun showWishBtn(isWished: Boolean) {
        if(isWished) binding.ivBtnWish.setImageResource(R.drawable.ic_btn_wish_fill)
        else binding.ivBtnWish.setImageResource(R.drawable.ic_btn_wish_empty)
    }



}
