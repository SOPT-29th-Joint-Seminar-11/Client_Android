package com.example.client_android.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.client_android.databinding.ItemSliderImgBinding

class ImageSliderAdapter : RecyclerView.Adapter<ImageSliderAdapter.ImageViewHolder>() {
    val dataList = mutableListOf<String>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImageViewHolder {
        var binding = ItemSliderImgBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ImageViewHolder,
        position: Int
    ) {
        holder.onBind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size

    class ImageViewHolder(private val binding: ItemSliderImgBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(url: String) {
            Glide.with(itemView)
                .load(url)
                .into(binding.ivSlider)




            // TODO : implement iv setOnClickListener
        }
    }
}