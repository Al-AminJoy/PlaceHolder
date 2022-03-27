package com.alamin.placeholder.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.alamin.placeholder.model.data.Photo
import com.alamin.placeholder.model.local.LocalDatabase
import com.alamin.placeholder.model.local.dao.PhotoDao
import com.alamin.placeholder.model.repository.PhotoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PhotoViewModel(application: Application): AndroidViewModel(application) {
    private val photoDao: PhotoDao = LocalDatabase.getDatabase(application).photoDao();
    private val photoRepository: PhotoRepository = PhotoRepository(photoDao);

    val photo: LiveData<Photo>
    get() = photoRepository.photo

    val photoList: LiveData<List<Photo>>
        get() = photoRepository.photoList

    suspend fun createPhoto(photo: Photo){
        viewModelScope.launch(Dispatchers.IO) {
            photoRepository.createPhoto(photo);
        }
    }

    suspend fun updatePhoto(photo: Photo){
        viewModelScope.launch(Dispatchers.IO) {
            photoRepository.updatePhoto(photo)
        }
    }

    suspend fun deletePhoto(photo: Photo){
        viewModelScope.launch(Dispatchers.IO) {
            photoRepository.deletePhoto(photo)
        }
    }

    fun findPhotoById(id: Int): LiveData<Photo>{
        return photoRepository.itemById(id);
    }

    fun getAllPhoto(): LiveData<List<Photo>>{
        return photoRepository.getAllPhoto();
    }
}