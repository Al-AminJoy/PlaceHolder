package com.alamin.placeholder.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.alamin.placeholder.model.data.Album
import javax.inject.Inject

class AlbumDiffUtils @Inject constructor() :
    DiffUtil.Callback() {
    private lateinit var oldList: List<Album>;
    private lateinit var newList: List<Album>

    fun setList( oldList: List<Album>,  newList: List<Album>){
        this.oldList = oldList
        this.newList = newList
    }

    override fun getOldListSize(): Int {
        return oldList.size;
    }

    override fun getNewListSize(): Int {
        return newList.size;
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].id != newList[newItemPosition].id -> false
            oldList[oldItemPosition].title != newList[newItemPosition].title -> false
            oldList[oldItemPosition].userId != newList[newItemPosition].userId -> false
            else -> true
        }
    }
}