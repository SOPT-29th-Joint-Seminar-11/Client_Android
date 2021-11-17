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
                binding.tvCafeName.text = data.cafeName
                binding.tvDistance.text = data.distance.toString() + "km"
                binding.tvKindLocation.text = data.kindAndLocation
                // rating bar binding.rbAverage.
                binding.tvAverageScore.text = data.averageScore.toString()
                binding.ivCafeImage.setImageResource(data.cafeImage) // 이렇게 해주는거 맞나 ?
                binding.tvReview.text = data.review
                binding.tvTimeAgo.text = data.timeAge.toString() + "초 전"
            }
        }
}