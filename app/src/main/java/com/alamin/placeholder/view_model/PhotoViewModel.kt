package com.alamin.placeholder.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.alamin.placeholder.model.local.LocalDatabase
import com.alamin.placeholder.model.local.dao.PhotoDao
import com.alamin.placeholder.model.repository.PhotoRepository

class PhotoViewModel(application: Application): AndroidViewModel(application) {
    private val photoDao: PhotoDao = LocalDatabase.getDatabase(application).photoDao();
    private val photoRepository: PhotoRepository = PhotoRepository(photoDao);
}