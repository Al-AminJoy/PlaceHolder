package com.alamin.placeholder.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.alamin.placeholder.model.data.Photo

class PhotoDiffUtils(private val oldList: List<Photo>, private val newList: List<Photo>) :
    DiffUtil.Callback() {
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