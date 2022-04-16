package com.alamin.placeholder.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alamin.placeholder.model.repository.PhotoRepository
import javax.inject.Inject

class PhotoViewModelFactory @Inject constructor(private val photoRepository: PhotoRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PhotoViewModel(photoRepository) as T;
    }
}