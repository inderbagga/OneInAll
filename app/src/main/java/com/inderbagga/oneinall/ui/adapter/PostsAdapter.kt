package com.inderbagga.oneinall.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.inderbagga.oneinall.R
import com.inderbagga.oneinall.data.model.Post
import com.inderbagga.oneinall.ui.adapter.PostsAdapter.*

class PostsAdapter(private var postList: ArrayList<Post>) : RecyclerView.Adapter<PostViewHolder>() {

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val id=itemView.findViewById(R.id.postId) as TextView
        val title=itemView.findViewById(R.id.postTitle) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false))
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {

        val post=postList[position]

        holder.apply {

            id.text=post.id.toString()
            title.text=post.title.toString()
        }
    }

    override fun getItemCount(): Int = postList.size

    fun update(posts: List<Post>) {

        postList= posts as ArrayList<Post>
        notifyDataSetChanged()
    }
}