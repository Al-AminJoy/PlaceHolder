package com.alamin.placeholder.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alamin.placeholder.model.data.Album
import com.alamin.placeholder.model.local.dao.AlbumDao
import com.alamin.placeholder.model.network.APIClient
import com.alamin.placeholder.model.network.ApiInterface
import retrofit2.Response

class AlbumRepository(val albumDao: AlbumDao) {
    val apiInterface: ApiInterface = APIClient.getInstance().create(ApiInterface::class.java);
    val albumLiveData = MutableLiveData<Album>();
    val albumLiveDataList = MutableLiveData<List<Album>>();

    val album: LiveData<Album>
        get() = albumLiveData;

    val albumList: LiveData<List<Album>>
        get() = albumLiveDataList

    suspend fun createAlbum(album: Album) {
        albumDao.createAlbum(album);
    }

    suspend fun insertAlbumList(albumList: List<Album>) {
        albumDao.insertAlbumList(albumList);
    }

    suspend fun updateAlbum(album: Album) {
        albumDao.updateAlbum(album);
    }

    suspend fun deleteAlbum(album: Album) {
        albumDao.deleteAlbum(album);
    }

    fun itemById(id: Int): LiveData<Album> {
        return albumDao.findAlbumById(id);
    }

    fun getAllAlbum(): LiveData<List<Album>> {
        return albumDao.getAllAlbum();
    }

    suspend fun getAlbumFromResponse(id: Int) {
        val result: Response<List<Album>> = apiInterface.getAllAlbum(id);
        result?.let {
            albumLiveDataList.postValue(it.body());
        }
    }
}