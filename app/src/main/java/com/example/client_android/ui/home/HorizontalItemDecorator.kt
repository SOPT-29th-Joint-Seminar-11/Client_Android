package com.example.client_android.ui.home

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class HorizontalItemDecorator(private val divHeight : Int) : RecyclerView.ItemDecoration() { // px값인가 ?

    @Override
    override fun getItemOffsets(outRect: Rect, view: View, parent : RecyclerView, state : RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.right = divHeight // 오른쪽만 여백을 준다
    }
}