package com.example.client_android.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.client_android.R
import com.example.client_android.databinding.RatingBarBinding
import kotlin.math.ceil

class RatingBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet?, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    private var binding: RatingBarBinding
    private val stars = mutableListOf<ImageView>()

    init {
        binding = RatingBarBinding.inflate(LayoutInflater.from(context), this, true)

        stars.addAll(
            listOf(
                binding.ivStar1, binding.ivStar2, binding.ivStar3, binding.ivStar4, binding.ivStar5
            )
        )
    }

    public fun setStar(num: Float) {
        val fill = R.drawable.ic_star_fill
        val empty = R.drawable.ic_star_empty

        for (i in 0..4) {
            stars[i].setImageResource(empty)
        }
        val n = num.toInt()
        if (n>0) stars[0].setImageResource(fill)
        if (n>1) stars[1].setImageResource(fill)
        if (n>2) stars[2].setImageResource(fill)
        if (n>3) stars[3].setImageResource(fill)
        if (n>4) stars[4].setImageResource(fill)
    }
}
