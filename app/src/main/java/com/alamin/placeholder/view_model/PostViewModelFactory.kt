package com.alamin.placeholder.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alamin.placeholder.model.repository.PostRepository
import javax.inject.Inject

class PostViewModelFactory @Inject constructor(private val repository: PostRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PostViewModel(repository) as T
    }
}