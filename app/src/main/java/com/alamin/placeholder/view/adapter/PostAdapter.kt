package com.alamin.placeholder.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alamin.placeholder.databinding.PostLayoutBinding
import com.alamin.placeholder.model.data.Post

class PostAdapter(private val postList: List<Post>) :
    RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
    private lateinit var postClickListener: PostClickListener

    inner class PostViewHolder(private val binding: PostLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) {
            binding.post = post
            binding.itemClickListener = postClickListener;
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostAdapter.PostViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context);
        val view = PostLayoutBinding.inflate(layoutInflater, parent, false);
        return PostViewHolder(view);
    }

    override fun onBindViewHolder(holder: PostAdapter.PostViewHolder, position: Int) {
        holder.bind(postList[position]);
    }

    override fun getItemCount(): Int {
        return postList.size;
    }

    fun setPostClickListener(postClickListener: PostClickListener) {
        this.postClickListener = postClickListener;
    }
}