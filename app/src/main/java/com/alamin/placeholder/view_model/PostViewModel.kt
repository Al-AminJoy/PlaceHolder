package com.alamin.placeholder.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.alamin.placeholder.model.data.Album
import com.alamin.placeholder.model.data.Post
import com.alamin.placeholder.model.local.LocalDatabase
import com.alamin.placeholder.model.local.dao.PostDao
import com.alamin.placeholder.model.repository.PostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostViewModel(application: Application): AndroidViewModel(application) {
    private val postDao: PostDao = LocalDatabase.getDatabase(application).postDao();
    private val postRepository: PostRepository = PostRepository(postDao);

    val post: LiveData<Post>
    get() = postRepository.post

    val postList: LiveData<List<Post>>
        get() = postRepository.postList

    suspend fun createPost(post: Post){
        viewModelScope.launch(Dispatchers.IO) {
            postRepository.createPost(post);
        }
    }

    suspend fun updatePost(post: Post){
        viewModelScope.launch(Dispatchers.IO) {
            postRepository.updatePost(post)
        }
    }

    suspend fun deletePost(post: Post){
        viewModelScope.launch(Dispatchers.IO) {
            postRepository.deletePost(post)
        }
    }

    fun findPostById(id: Int): LiveData<Post>{
        return postRepository.itemById(id);
    }

    fun getAllPost(): LiveData<List<Post>>{
        return postRepository.getAllPost();
    }
}