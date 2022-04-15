package com.alamin.placeholder.view_model

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alamin.placeholder.model.data.Post
import com.alamin.placeholder.model.local.LocalDatabase
import com.alamin.placeholder.model.local.dao.PostDao
import com.alamin.placeholder.model.network.OnResponseCall
import com.alamin.placeholder.model.repository.PostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "PostViewModel"

class PostViewModel @Inject constructor (private val repository: PostRepository) : ViewModel() {

    val post: LiveData<Post>
        get() = repository.post

    val postList: LiveData<List<Post>>
        get() = repository.postList

    fun createPost(post: Post) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.createPost(post);
        }
    }

    fun createPostToServer(post: Post, onResponseCall: OnResponseCall) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.createPostToServer(post);
            if (response) {
                createPost(post)
                onResponseCall.onSuccess("Success");
            } else {
                onResponseCall.onFailed("Failed");
            }
        }
    }


    fun insertPostList(post: List<Post>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertPostList(post);
        }
    }

    fun updatePost(post: Post) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updatePost(post)
        }
    }

    fun updatePostToServer(post: Post, onResponseCall: OnResponseCall) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.updatePostToServer(post);
            if (response) {
                updatePost(post);
                onResponseCall.onSuccess("Success")
            } else {
                onResponseCall.onFailed("Failed")
            }
        }
    }

    fun deletePost(post: Post) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deletePost(post)
        }
    }

    fun findPostById(id: Int): LiveData<Post> {
        return repository.itemById(id);
    }

    fun getAllPost(): LiveData<List<Post>> {
        return repository.getAllPost();
    }

    fun getPostFromResponseByUserId(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getPostByUserFromResponse(id)
        }
    }
}