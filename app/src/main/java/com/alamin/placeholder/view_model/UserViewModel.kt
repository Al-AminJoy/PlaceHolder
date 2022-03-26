package com.alamin.placeholder.view_model

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.databinding.Bindable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.alamin.placeholder.model.data.User
import com.alamin.placeholder.model.local.LocalDatabase
import com.alamin.placeholder.model.local.dao.UserDao
import com.alamin.placeholder.model.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {
    private  val userDao: UserDao = LocalDatabase.getDatabase(application).userDao();
    private  val userRepository: UserRepository = UserRepository(userDao);


    val user: LiveData<User>
    get() = userRepository.user;

     fun getUserResponse(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.getUserFromResponse(id);
        }
    }

    suspend fun createUser(user:User){
        userRepository.createUser(user);
    }
}