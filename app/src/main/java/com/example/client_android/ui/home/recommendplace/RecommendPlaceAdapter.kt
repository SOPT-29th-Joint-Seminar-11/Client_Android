package com.example.client_android.ui.home.recommendplace

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.client_android.R
import com.example.client_android.databinding.ItemRecommendPlaceListBinding
import com.example.client_android.network.model.ResponseHomeData


class RecommendPlaceAdapter : RecyclerView.Adapter<RecommendPlaceAdapter.RecommendPlaceViewHolder>() {
    val recommendplaceList = mutableListOf<ResponseHomeData.Data>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendPlaceViewHolder {
        var binding = ItemRecommendPlaceListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return RecommendPlaceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecommendPlaceViewHolder, position: Int) {
        holder.onBind(recommendplaceList[position])

        //view에 onClickListner를 달고, 그 안에서 직접 만든 itemClickListener를 연결시킨다
        holder.itemView.setOnClickListener {
            itemClickListner.onClick(it, position)
        }
    }

    override fun getItemCount(): Int = recommendplaceList.size

    class RecommendPlaceViewHolder(private val binding: ItemRecommendPlaceListBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun onBind(data: ResponseHomeData.Data){
                with(binding) {
                    Glide.with(ivPlaceImage)
                        .load(data.titleImage)
                        .into(ivPlaceImage)

                    tvPlaceName.text = data.name
                    /* data 로 넘겨준 averageScore 에 따라 2.5점 이상이면 꽉 차있는 별, 아니면 비어있는 별 이미지 */
                    if(data.rating>= 2.5){
                        ivAverageStar.setImageResource(R.drawable.ic_star_fill)
                    }else{
                        ivAverageStar.setImageResource(R.drawable.ic_star_empty)
                    }
                    tvAverageScore.text = data.rating.toString()
                    tvReviewCnt.text  = "(" + data.reviewCount + ")"
                    tvCafeLocation.text = data.groupType + "·" + data.location

                    /* 즉시 예약 & 원격 줄서기 */
                    if(data.reserveFlag && data.lineupFlag) {
                        tvFastReservation.text = "즉시예약"
                        tvRemoteWaiting.text = "원격줄서기"
                    }
                    else if(data.reserveFlag) {
                        tvFastReservation.text = "즉시예약"
                        tvRemoteWaiting.visibility = View.INVISIBLE
                    }
                    else if(data.lineupFlag) {
                        tvFastReservation.text = "원격줄서기"
                        tvRemoteWaiting.visibility = View.INVISIBLE
                    }
                    else { // 둘다 false 이면
                        tvFastReservation.visibility = View.INVISIBLE
                        tvRemoteWaiting.visibility = View.INVISIBLE
                    }
                }

            }
    }

    interface ItemClickListener {
        fun onClick(view: View, position: Int)
    }

    //클릭리스너 선언
    private lateinit var itemClickListner: ItemClickListener

    //클릭리스너 등록 매소드
    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListner = itemClickListener
    }


}