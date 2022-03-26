package com.alamin.placeholder.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.alamin.placeholder.model.local.LocalDatabase
import com.alamin.placeholder.model.local.dao.PostDao
import com.alamin.placeholder.model.repository.PostRepository

class PostViewModel(application: Application): AndroidViewModel(application) {
    private val postDao: PostDao = LocalDatabase.getDatabase(application).postDao();
    private val postRepository: PostRepository = PostRepository(postDao);
}