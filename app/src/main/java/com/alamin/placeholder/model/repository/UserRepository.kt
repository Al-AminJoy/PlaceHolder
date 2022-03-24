package com.alamin.placeholder.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alamin.placeholder.model.data.User
import com.alamin.placeholder.model.local.dao.UserDao
import com.alamin.placeholder.model.network.APIClient
import com.alamin.placeholder.model.network.ApiInterface

class UserRepository(private val userDao: UserDao) {
    var apiInterface: ApiInterface? = APIClient.getInstance().create(ApiInterface::class.java);
    val userLiveData = MutableLiveData<User>();

    val user: LiveData<User>
    get() = userLiveData

    suspend fun getUserFromResponse(id: Int){
        val result = apiInterface?.getUserById(id);
        if (result?.body() != null){
            userLiveData.postValue(result.body());
        }
    }

     fun itemById(id: Int): LiveData<User>{
        return userDao.findItemById(id);
    }

    suspend fun createUser(user: User){
        userDao.createUser(user);
    }

    suspend fun updateUser(user: User){
        userDao.updateUser(user);
    }

    suspend fun deleteUser(user: User){
        userDao.deleteUser(user);
    }


}