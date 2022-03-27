package com.alamin.placeholder.model.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.alamin.placeholder.model.data.Post

@Dao
interface PostDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun createPost(post: Post);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPostList(posts: List<Post>);

    @Update
    suspend fun updatePost(post: Post);

    @Delete
    suspend fun deletePost(post: Post);

    @Query("SELECT * FROM post")
    fun getAllPost(): LiveData<List<Post>>

    @Query("SELECT * FROM post WHERE id=:id")
    fun findPostById(id: Int): LiveData<Post>
}