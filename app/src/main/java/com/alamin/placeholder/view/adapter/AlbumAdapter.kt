package com.alamin.placeholder.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alamin.placeholder.databinding.GalleryLayoutBinding
import com.alamin.placeholder.model.data.Album

class AlbumAdapter(private val albumList: List<Album>): RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> (){

    inner class AlbumViewHolder(val binding: GalleryLayoutBinding): RecyclerView.ViewHolder(binding.root){
      fun binding(album: Album){
            binding.album = album;
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AlbumAdapter.AlbumViewHolder {
        var inflater = LayoutInflater.from(parent.context);
        return AlbumViewHolder(GalleryLayoutBinding.inflate(inflater,parent,false))
    }

    override fun onBindViewHolder(holder: AlbumAdapter.AlbumViewHolder, position: Int) {
        holder.binding(albumList[position])
    }

    override fun getItemCount(): Int {
        return albumList.size;
    }
}