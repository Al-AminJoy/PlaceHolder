package com.alamin.placeholder.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.alamin.placeholder.model.data.Album
import com.alamin.placeholder.model.data.Comment
import com.alamin.placeholder.model.local.LocalDatabase
import com.alamin.placeholder.model.local.dao.CommentDao
import com.alamin.placeholder.model.repository.CommentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CommentViewModel(application: Application) : AndroidViewModel(application) {
    private val commentDao: CommentDao = LocalDatabase.getDatabase(application).commentDao();
    private val commentRepository: CommentRepository = CommentRepository(commentDao);

    val comment: LiveData<Comment>
        get() = commentRepository.comment

    val commentList: LiveData<List<Comment>>
        get() = commentRepository.commentList

    fun createComment(comment: Comment) {
        viewModelScope.launch(Dispatchers.IO) {
            commentRepository.createComment(comment);
        }
    }

    fun updateComment(comment: Comment) {
        viewModelScope.launch(Dispatchers.IO) {
            commentRepository.updateComment(comment)
        }
    }

    fun deleteComment(comment: Comment) {
        viewModelScope.launch(Dispatchers.IO) {
            commentRepository.deleteComment(comment)
        }
    }

    fun findCommentById(id: Int): LiveData<Comment> {
        return commentRepository.itemById(id);
    }

    fun getAllComment(): LiveData<List<Comment>> {
        return commentRepository.getAllComment();
    }
}