package com.alamin.placeholder.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.alamin.placeholder.model.data.Album
import com.alamin.placeholder.model.data.User
import com.alamin.placeholder.model.local.LocalDatabase
import com.alamin.placeholder.model.local.dao.AlbumDao
import com.alamin.placeholder.model.repository.AlbumRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlbumViewModel(application: Application) : AndroidViewModel(application) {
    private val albumDao: AlbumDao = LocalDatabase.getDatabase(application).albumDao();
    private val albumRepository: AlbumRepository = AlbumRepository(albumDao);

    val album: LiveData<Album>
        get() = albumRepository.album

    val albumList: LiveData<List<Album>>
        get() = albumRepository.albumList

    fun createAlbum(album: Album) {
        viewModelScope.launch(Dispatchers.IO) {
            albumRepository.createAlbum(album);
        }
    }

    fun insertAlbumList(albumList: List<Album>) {
        viewModelScope.launch(Dispatchers.IO) {
            albumRepository.insertAlbumList(albumList);
        }
    }

    fun updateAlbum(album: Album) {
        viewModelScope.launch(Dispatchers.IO) {
            albumRepository.updateAlbum(album)
        }
    }

    fun deleteAlbum(album: Album) {
        viewModelScope.launch(Dispatchers.IO) {
            albumRepository.deleteAlbum(album)
        }
    }

    fun findAlbumById(id: Int): LiveData<Album> {
        return albumRepository.itemById(id);
    }

    fun getAllAlbum(): LiveData<List<Album>> {
        return albumRepository.getAllAlbum();
    }

    fun getAlbumFromResponse(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            albumRepository.getAlbumFromResponse(id)
        }
    }
}