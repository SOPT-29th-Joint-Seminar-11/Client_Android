package com.example.client_android.util

import android.view.View
import androidx.annotation.Px
import kotlin.math.roundToInt

@Px
fun View.px(dp: Int) = (dp * resources.displayMetrics.density).roundToInt()

object ViewUtil {
}