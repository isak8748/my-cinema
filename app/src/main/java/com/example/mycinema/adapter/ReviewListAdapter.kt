package com.example.mycinema.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mycinema.databinding.ReviewListItemBinding
import com.example.mycinema.model.Review

class ReviewListAdapter() : ListAdapter<Review, ReviewListAdapter.ViewHolder>(ReviewListDiffCallback()){
    class ViewHolder(private var binding: ReviewListItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(review: Review){
            binding.review = review
            binding.executePendingBindings()
        }
        companion object {
            fun from(parent: ViewGroup) : ReviewListAdapter.ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ReviewListItemBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewListAdapter.ViewHolder {
        return ReviewListAdapter.ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ReviewListAdapter.ViewHolder, position: Int) {
        return holder.bind(getItem(position))
    }
}

class ReviewListDiffCallback : DiffUtil.ItemCallback<Review>() {
    override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean {
        return oldItem == newItem
    }

}