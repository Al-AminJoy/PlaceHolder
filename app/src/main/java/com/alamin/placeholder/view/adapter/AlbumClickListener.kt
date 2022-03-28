package com.alamin.placeholder.view.adapter

import com.alamin.placeholder.model.data.Album

interface AlbumClickListener {
    fun onItemClicked(album: Album)
}