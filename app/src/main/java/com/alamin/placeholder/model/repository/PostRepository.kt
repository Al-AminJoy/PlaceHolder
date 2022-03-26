package com.alamin.placeholder.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alamin.placeholder.model.data.Post
import com.alamin.placeholder.model.local.dao.PostDao
import com.alamin.placeholder.model.network.APIClient
import com.alamin.placeholder.model.network.ApiInterface

class PostRepository(val postDao: PostDao) {
    val apiInterface: ApiInterface = APIClient.getInstance().create(ApiInterface::class.java);
    val postLiveData = MutableLiveData<Post>();
    val postLiveDataList = MutableLiveData<List<Post>>();

    val post:LiveData<Post>
    get() = postLiveData;

    val postList: LiveData<List<Post>>
    get() = postLiveDataList

    suspend fun createPost(post: Post){
        postDao.createPost(post);
    }

    suspend fun updatePost(post: Post){
        postDao.updatePost(post);
    }

    suspend fun deletePost(post: Post){
        postDao.deletePost(post);
    }

    fun itemById(id: Int): LiveData<Post>{
        return postDao.findPostById(id);
    }

    fun getAllPost(): LiveData<List<Post>>{
        return postDao.getAllPost();
    }
}