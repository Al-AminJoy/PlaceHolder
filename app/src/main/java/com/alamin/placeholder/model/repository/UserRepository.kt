package com.alamin.placeholder.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alamin.placeholder.model.data.User
import com.alamin.placeholder.model.local.LocalDatabase
import com.alamin.placeholder.model.local.dao.UserDao
import com.alamin.placeholder.model.network.APIClient
import com.alamin.placeholder.model.network.ApiInterface
import javax.inject.Inject

class UserRepository @Inject constructor (private val apiInterface: ApiInterface, private val localDatabase: LocalDatabase) {
    val userDao = localDatabase.userDao()
    val userLiveData = MutableLiveData<User>();

    val user: LiveData<User>
    get() = userLiveData

    suspend fun getUserFromResponse(id: Int){
        val result = apiInterface.getUserById(id);
        result?.let {
            userLiveData.postValue(result.body());
        }
    }

    fun itemById(id: Int): LiveData<User>{
        return userDao.findItemById(id);
    }

    fun getAllUser(): LiveData<List<User>>{
        return userDao.getAllUser();
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