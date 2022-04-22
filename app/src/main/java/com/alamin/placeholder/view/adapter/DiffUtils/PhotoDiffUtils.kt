package com.alamin.placeholder.view.adapter.DiffUtils

import androidx.recyclerview.widget.DiffUtil
import com.alamin.placeholder.model.data.Album
import com.alamin.placeholder.model.data.Photo
import javax.inject.Inject

class PhotoDiffUtils @Inject constructor() :
    DiffUtil.Callback() {

    private lateinit var oldList: List<Photo>;
    private lateinit var newList: List<Photo>

    fun setList(oldList: List<Photo>, newList: List<Photo>){
        this.oldList = oldList
        this.newList = newList
    }

    override fun getOldListSize(): Int {
        return oldList.size;
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id;
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].id != newList[newItemPosition].id -> false;
            oldList[oldItemPosition].albumId != newList[newItemPosition].albumId -> false;
            oldList[oldItemPosition].thumbnailUrl != newList[newItemPosition].thumbnailUrl -> false;
            oldList[oldItemPosition].title != newList[newItemPosition].title -> false;
            oldList[oldItemPosition].url != newList[newItemPosition].url -> false;
            else -> true
        }
    }
}