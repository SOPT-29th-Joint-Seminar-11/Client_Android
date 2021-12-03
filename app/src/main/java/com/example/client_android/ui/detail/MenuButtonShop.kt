package com.example.client_android.ui.detail

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.client_android.R
import com.example.client_android.databinding.MenuButtonShopBinding

class MenuButtonShop @JvmOverloads constructor(
    context: Context, attrs: AttributeSet?, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    private var binding: MenuButtonShopBinding
    // wish (하트) 개수
    private var wishes = 0
    // wish가 선택되었는지
    private var isWished = false

    init {
        binding = MenuButtonShopBinding.inflate(LayoutInflater.from(context), this, true)

        // initWishes()
        initListener()
    }

    // 서버에서 받은 데이터로 wishes, isWished 초기화
    fun initData(wishes: Int, isWished: Boolean) {
        this.wishes = wishes
        this.isWished = isWished

        initWishes()
    }

    private fun initWishes() {
        // TODO ( wishes, isWished 서버에서 전송받기)

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
            if(isWished) wishes--
            else wishes++
            isWished = !isWished
            binding.tvShopLikes.text = wishes.toString()
            showWishBtn(isWished)
        }
    }

    private fun showWishBtn(isWished: Boolean) {
        if(isWished) binding.ivBtnWish.setImageResource(R.drawable.ic_btn_wish_fill)
        else binding.ivBtnWish.setImageResource(R.drawable.ic_btn_wish_empty)
    }



}
