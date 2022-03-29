package com.alamin.placeholder.view.adapter

import com.alamin.placeholder.model.data.Post

interface PostClickListener {
    fun onItemClick(post: Post)
}