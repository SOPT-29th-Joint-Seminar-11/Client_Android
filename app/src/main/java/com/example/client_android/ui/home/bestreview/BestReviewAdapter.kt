package com.example.client_android.ui.home.bestreview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.client_android.databinding.ItemBestReviewListBinding

class BestReviewAdapter : RecyclerView.Adapter<BestReviewAdapter.BestReviewViewHolder>() {
    val reviewList = mutableListOf<ReviewData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestReviewViewHolder {
        val binding = ItemBestReviewListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )

        return BestReviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BestReviewViewHolder, position: Int) {
        holder.onBind(reviewList[position])
    }

    override fun getItemCount(): Int = reviewList.size

    class BestReviewViewHolder(private val binding: ItemBestReviewListBinding)
        : RecyclerView.ViewHolder(binding.root){
            fun onBind(data: ReviewData){
                with(binding) {
                    tvPlaceName.text = data.placeName
                    tvDistance.text = data.distance.toString() + "km"
                    tvKindLocation.text = data.kindAndLocation
                    rbBestReview.setStar(data.numOfStars) // rating bar에 별 개수 전달
                    tvAverageScore.text = data.averageScore.toString()
                    ivPlaceImage.setImageResource(data.placeImage) //
                    tvReview.text = data.review
                    tvTimeAgo.text = data.timeAge.toString() + "초 전"
                }
            }
        }
}