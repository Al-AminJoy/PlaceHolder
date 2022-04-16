package com.alamin.placeholder.view_model

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.databinding.Bindable
import androidx.lifecycle.*
import com.alamin.placeholder.model.data.User
import com.alamin.placeholder.model.local.LocalDatabase
import com.alamin.placeholder.model.local.dao.UserDao
import com.alamin.placeholder.model.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    val user: LiveData<User>
        get() = userRepository.user;

    fun getUserResponse(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.getUserFromResponse(id);
        }
    }

    fun createUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.createUser(user);
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.updateUser(user)
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.deleteUser(user)
        }
    }

    fun findUserById(id: Int): LiveData<User> {
        return userRepository.itemById(id);
    }

    fun getAllUser(): LiveData<List<User>> {
        return userRepository.getAllUser();
    }
}