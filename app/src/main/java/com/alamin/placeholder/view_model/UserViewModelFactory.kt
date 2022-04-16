package com.alamin.placeholder.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alamin.placeholder.model.repository.UserRepository
import javax.inject.Inject

class UserViewModelFactory @Inject constructor(private val userRepository: UserRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserViewModel(userRepository) as T;
    }
}