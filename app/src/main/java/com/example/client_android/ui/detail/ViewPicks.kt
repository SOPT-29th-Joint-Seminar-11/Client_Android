package com.example.client_android.ui.detail

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import com.example.client_android.R
import com.example.client_android.databinding.ViewPicksBinding
import com.google.android.flexbox.FlexboxLayout

class ViewPicks @JvmOverloads constructor(
    context: Context, attrs: AttributeSet?, defStyleAttr: Int = 0
) : FlexboxLayout(context, attrs, defStyleAttr) {
    private var binding: ViewPicksBinding
    // dp 계산에 필요
    private val scale = resources.displayMetrics.density
    //
    private val pickList = mutableListOf<String>()

    init {
        binding = ViewPicksBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun setPicks(list: Collection<String>) {
        pickList.clear()
        pickList.addAll(list)
        for(i in list) {
            addTextView(i)
        }
    }

    fun addPick(pick: String) {
        pickList.add(pick)
        addTextView(pick)
    }

    private fun addTextView(str: String) {
        val tv = TextView(context)

        tv.text = str
        tv.setTextAppearance(R.style.pick_text)

        tv.setPadding(getDP(11), 0, getDP(11), 0)

        val layoutParams = LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(0, getDP(8), getDP(8), 0)
        tv.layoutParams = layoutParams

        tv.setBackgroundResource(R.drawable.rectangle_border_red_radius_17dp)
        binding.fblCategoryContainer.addView(tv)

    }

    private fun getDP(value: Int): Int = (value * scale + 0.5f).toInt()

}