package com.alamin.placeholder.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
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

    fun createPost(post: Post){
        viewModelScope.launch(Dispatchers.IO) {
            postRepository.createPost(post);
        }
    }

    fun insertPostList(post: List<Post>){
        viewModelScope.launch(Dispatchers.IO) {
            postRepository.insertPostList(post);
        }
    }

    fun updatePost(post: Post){
        viewModelScope.launch(Dispatchers.IO) {
            postRepository.updatePost(post)
        }
    }

    fun deletePost(post: Post){
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

     fun getPostFromResponseByUserId(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            postRepository.getPostByUserFromResponse(id)
        }
    }
}