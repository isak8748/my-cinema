package com.example.mycinema.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mycinema.databinding.ReviewListItemBinding
import com.example.mycinema.databinding.VideoListItemBinding
import com.example.mycinema.model.Review
import com.example.mycinema.model.Video

class VideoListAdapter() : ListAdapter<Video, VideoListAdapter.ViewHolder>(VideoListDiffCallback()){
    class ViewHolder(private var binding: VideoListItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(video: Video){
            binding.video = video
            binding.videoListItemWebview.settings.javaScriptEnabled = true
            if(video.site == "YouTube"){
                val myWebView = binding.videoListItemWebview
                val str = "<iframe width=\"300\" height=\"200\" src=\"https://www.youtube.com/embed/" + video.key + "\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>"
                myWebView.loadData(str, "text/html", null)
            }
            binding.executePendingBindings()
        }
        companion object {
            fun from(parent: ViewGroup) : VideoListAdapter.ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = VideoListItemBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoListAdapter.ViewHolder {
        return VideoListAdapter.ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: VideoListAdapter.ViewHolder, position: Int) {
        return holder.bind(getItem(position))
    }
}

class VideoListDiffCallback : DiffUtil.ItemCallback<Video>() {
    override fun areItemsTheSame(oldItem: Video, newItem: Video): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Video, newItem: Video): Boolean {
        return oldItem == newItem
    }

}