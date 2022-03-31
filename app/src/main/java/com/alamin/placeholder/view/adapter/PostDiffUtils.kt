package com.alamin.placeholder.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.alamin.placeholder.model.data.Post

class PostDiffUtils(
    private val oldList: List<Post>,
    private val newList: List<Post>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id;
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].id != newList[newItemPosition].id -> false
            oldList[oldItemPosition].title != newList[newItemPosition].title -> false
            oldList[oldItemPosition].body != newList[newItemPosition].body -> false
            oldList[oldItemPosition].userId != newList[newItemPosition].userId -> false
            else -> true
        }
    }
}