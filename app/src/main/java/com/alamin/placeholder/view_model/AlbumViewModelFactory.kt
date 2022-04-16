package com.alamin.placeholder.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alamin.placeholder.model.repository.AlbumRepository
import javax.inject.Inject

class AlbumViewModelFactory @Inject constructor(private val albumRepository: AlbumRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AlbumViewModel(albumRepository) as T;
    }
}