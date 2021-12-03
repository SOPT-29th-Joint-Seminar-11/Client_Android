package com.example.client_android.ui.home.recommendplace

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.client_android.R
import com.example.client_android.databinding.ItemHotPlaceListBinding


class HotPlaceAdapter : RecyclerView.Adapter<HotPlaceAdapter.HotPlaceViewHolder>() {
    val placeList = mutableListOf<PlaceData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotPlaceViewHolder {
        var binding = ItemHotPlaceListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return HotPlaceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HotPlaceViewHolder, position: Int) {
        holder.onBind(placeList[position])

        //view에 onClickListner를 달고, 그 안에서 직접 만든 itemClickListener를 연결시킨다
        holder.itemView.setOnClickListener {
            itemClickListner.onClick(it, position)
        }
    }

    override fun getItemCount(): Int = placeList.size


    class HotPlaceViewHolder(private val binding: ItemHotPlaceListBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: PlaceData){
            with(binding) {
                ivPlaceImage.setImageResource(data.placeImage)
                tvPlaceName.text = data.placeName

                /* data 로 넘겨준 averageScore 에 따라 2.5점 이상이면 꽉 차있는 별, 아니면 비어있는 별 이미지 */
                if(data.averageScore >= 2.5f)
                    ivAverageStar.setImageResource(R.drawable.ic_star_fill)
                else
                    ivAverageStar.setImageResource(R.drawable.ic_star_empty)
                tvAverageScore.text = data.averageScore.toString()

                tvReviewCnt.text  = "(" + data.reviewCnt + ")"
                tvCafeLocation.text = data.kind + "·" + data.location


                /* 즉시 예약 & 원격 줄서기 */
                if(data.fastReservation && data.remoteWaiting) {
                    tvFastReservation.text = "즉시예약"
                    tvRemoteWaiting.text = "원격줄서기"
                }
                else if(data.fastReservation) {
                    tvFastReservation.text = "즉시예약"
                    tvRemoteWaiting.visibility = View.INVISIBLE
                }
                else if(data.remoteWaiting) {
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