package com.alamin.placeholder.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alamin.placeholder.databinding.LayoutPhotoBinding
import com.alamin.placeholder.model.data.Photo

class PhotoAdapter() :
    RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {
    private var photoList: List<Photo> = emptyList()
    inner class PhotoViewHolder(val binding: LayoutPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun binding(photo: Photo) {
            binding.photo = photo;
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context);
        return PhotoViewHolder(LayoutPhotoBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.binding(photoList[position])
    }

    override fun getItemCount(): Int {
        return photoList.size
    }

    fun setData(newPhotoList: List<Photo>){
        val diffUtils = PhotoDiffUtils(photoList, newPhotoList);
        val diffResult = DiffUtil.calculateDiff(diffUtils);
        photoList = newPhotoList;
        diffResult.dispatchUpdatesTo(this);
    }

}