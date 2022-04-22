package com.alamin.placeholder.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alamin.placeholder.databinding.GalleryLayoutBinding
import com.alamin.placeholder.model.data.Album
import com.alamin.placeholder.view.adapter.DiffUtils.AlbumDiffUtils
import javax.inject.Inject

class AlbumAdapter @Inject constructor( private val albumDiffUtils: AlbumDiffUtils) :
    RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {
    private var albumList: List<Album> = emptyList()
    private var albumClickListener: AlbumClickListener? = null;

    inner class AlbumViewHolder(val binding: GalleryLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun binding(album: Album) {
            binding.album = album;
            binding.itemClick = albumClickListener
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AlbumAdapter.AlbumViewHolder {
        var inflater = LayoutInflater.from(parent.context);
        return AlbumViewHolder(GalleryLayoutBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: AlbumAdapter.AlbumViewHolder, position: Int) {
        holder.binding(albumList[position])
    }

    override fun getItemCount(): Int {
        return albumList.size;
    }

    fun setOnClickItem(albumClickListener: AlbumClickListener) {
        this.albumClickListener = albumClickListener;
    }

    fun setData(newAlbumList : List<Album>){
        albumDiffUtils.setList(albumList,newAlbumList)
        val diffResult = DiffUtil.calculateDiff(albumDiffUtils);
        albumList = newAlbumList;
        diffResult.dispatchUpdatesTo(this)
    }

}