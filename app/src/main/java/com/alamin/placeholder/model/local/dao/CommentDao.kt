package com.alamin.placeholder.model.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.alamin.placeholder.model.data.Comment

@Dao
interface CommentDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun createComment(comment: Comment);

    @Update
    suspend fun updateComment(comment: Comment);

    @Delete
    suspend fun deleteComment(comment: Comment);

    @Query("SELECT * FROM comment")
    fun getAllComment(): LiveData<List<Comment>>

    @Query("SELECT * FROM comment WHERE id=:id")
    fun findCommentById(id: Int): LiveData<Comment>
}