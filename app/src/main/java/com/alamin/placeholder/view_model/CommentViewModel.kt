package com.alamin.placeholder.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.alamin.placeholder.model.local.LocalDatabase
import com.alamin.placeholder.model.local.dao.CommentDao
import com.alamin.placeholder.model.repository.CommentRepository

class CommentViewModel(application: Application): AndroidViewModel(application){
    private val commentDao: CommentDao = LocalDatabase.getDatabase(application).commentDao();
    private val commentRepository: CommentRepository = CommentRepository(commentDao);
}