package com.inderbagga.oneinall.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.inderbagga.oneinall.data.model.Post
import com.inderbagga.oneinall.databinding.ListItemBinding
import com.inderbagga.oneinall.ui.adapter.PostsAdapter.*

class PostsAdapter : ListAdapter<Post, PostViewHolder>(
    TaskDiffCallBack()) {

    //This check runs on background thread
    class TaskDiffCallBack : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.id == newItem.id;
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {

        val listItemBinding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PostViewHolder(listItemBinding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {

        val itemPost = getItem(position)

        itemPost?.let {
            holder.bind(it)
        }
    }

    inner class PostViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(it: Post) {
            binding.post=it
            binding.executePendingBindings()
        }
    }
}