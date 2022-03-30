package com.alamin.placeholder.model.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alamin.placeholder.model.data.Photo
import com.alamin.placeholder.model.local.dao.PhotoDao
import com.alamin.placeholder.model.network.APIClient
import com.alamin.placeholder.model.network.ApiInterface
import retrofit2.Response

private const val TAG = "PhotoRepository"

class PhotoRepository(val photoDao: PhotoDao) {
    val apiInterface: ApiInterface = APIClient.getInstance().create(ApiInterface::class.java)
    val photoLiveData = MutableLiveData<Photo>();
    val photoLiveDataList = MutableLiveData<List<Photo>>();

    val photo: LiveData<Photo>
        get() = photoLiveData;

    val photoList: LiveData<List<Photo>>
        get() = photoLiveDataList

    suspend fun createPhoto(photo: Photo) {
        photoDao.createPhoto(photo);
    }

    suspend fun insertPhotoList(photos: List<Photo>) {
        photoDao.insertPhotoList(photos);
    }

    suspend fun updatePhoto(photo: Photo) {
        photoDao.updatePhoto(photo);
    }

    suspend fun deletePhoto(photo: Photo) {
        photoDao.deletePhoto(photo);
    }

    fun itemById(id: Int): LiveData<Photo> {
        return photoDao.findPhotoById(id);
    }

    fun getAllPhoto(): LiveData<List<Photo>> {
        return photoDao.getAllPhoto();
    }

    suspend fun getAllPhotoFromResponse(id: Int) {
        val result: Response<List<Photo>> = apiInterface.getAllPhoto(id);
        result?.let {
            photoLiveDataList.postValue(result.body())
        }
    }
}