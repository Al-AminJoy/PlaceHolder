package com.alamin.placeholder.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alamin.placeholder.model.data.Album
import com.alamin.placeholder.model.data.Comment
import com.alamin.placeholder.model.local.dao.CommentDao
import com.alamin.placeholder.model.network.APIClient
import com.alamin.placeholder.model.network.ApiInterface

class CommentRepository(val commentDao: CommentDao) {
    val apiInterface: ApiInterface = APIClient.getInstance().create(ApiInterface::class.java);
    val commentLiveData = MutableLiveData<Comment>();
    val commentLieDataList = MutableLiveData<List<Comment>>();

    val comment: LiveData<Comment>
        get() = commentLiveData;

    val postList: LiveData<List<Comment>>
        get() = commentLieDataList

    suspend fun createComment(comment: Comment){
        commentDao.createComment(comment);
    }

    suspend fun updateComment(comment: Comment){
        commentDao.updateComment(comment);
    }

    suspend fun deleteComment(comment: Comment){
        commentDao.deleteComment(comment);
    }

    fun itemById(id: Int): LiveData<Comment>{
        return commentDao.findCommentById(id);
    }

    fun getAllComment(): LiveData<List<Comment>>{
        return commentDao.getAllComment();
    }
}