package com.alamin.placeholder.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.alamin.placeholder.model.local.LocalDatabase
import com.alamin.placeholder.model.local.dao.AlbumDao
import com.alamin.placeholder.model.repository.AlbumRepository

class AlbumViewModel(application: Application): AndroidViewModel(application) {
    private val albumDao: AlbumDao = LocalDatabase.getDatabase(application).albumDao();
    private val albumRepository: AlbumRepository = AlbumRepository(albumDao);

}