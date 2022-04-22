package com.alamin.placeholder.view.adapter.DiffUtils

import androidx.recyclerview.widget.DiffUtil
import com.alamin.placeholder.model.data.Album
import com.alamin.placeholder.model.data.Post
import javax.inject.Inject

class PostDiffUtils @Inject constructor () : DiffUtil.Callback() {

    private lateinit var oldList: List<Post>;
    private lateinit var newList: List<Post>

    fun setList(oldList: List<Post>, newList: List<Post>){
        this.oldList = oldList
        this.newList = newList
    }

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