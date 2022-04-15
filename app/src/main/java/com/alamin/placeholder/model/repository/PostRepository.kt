package com.alamin.placeholder.model.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alamin.placeholder.model.data.Post
import com.alamin.placeholder.model.local.LocalDatabase
import com.alamin.placeholder.model.local.dao.PostDao
import com.alamin.placeholder.model.network.APIClient
import com.alamin.placeholder.model.network.ApiInterface
import javax.inject.Inject

private const val TAG = "PostRepository"

class PostRepository @Inject constructor(private val apiInterface: ApiInterface, private val localDatabase: LocalDatabase) {
    val postDao = localDatabase.postDao();
    val postLiveData = MutableLiveData<Post>();
    val postLiveDataList = MutableLiveData<List<Post>>();

    val post: LiveData<Post>
        get() = postLiveData;

    val postList: LiveData<List<Post>>
        get() = postLiveDataList

    suspend fun createPost(post: Post) {
        postDao.createPost(post);
    }

    suspend fun createPostToServer(post: Post): Boolean {
        val response = apiInterface.createPost(post);
        response?.let {
            return response.isSuccessful
        }
    }

    suspend fun insertPostList(post: List<Post>) {
        postDao.insertPostList(post);
    }

    suspend fun updatePost(post: Post) {
        postDao.updatePost(post);
    }

    suspend fun updatePostToServer(post: Post): Boolean {
        val result = apiInterface.updatePost(post.id);
        result?.let {
            return result.isSuccessful
        }
    }

    suspend fun deletePost(post: Post) {
        postDao.deletePost(post);
    }

    fun itemById(id: Int): LiveData<Post> {
        return postDao.findPostById(id);
    }

    fun getAllPost(): LiveData<List<Post>> {
        return postDao.getAllPost();
    }

    suspend fun getPostByUserFromResponse(id: Int) {
        val result = apiInterface.getAllPost();
        var list = arrayListOf<Post>()
        result?.let {
            result.body()?.forEach {
                if (it.userId == id) {
                    list.add(it)
                }
            }
            postLiveDataList.postValue(list);
        }
    }
}