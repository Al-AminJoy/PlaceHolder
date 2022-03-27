package com.alamin.placeholder.model.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.alamin.placeholder.model.data.Album
import com.alamin.placeholder.model.data.User
import java.nio.charset.CodingErrorAction.REPLACE

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createUser(user: User);

    @Update
    suspend fun updateUser(user: User);

    @Query("SELECT * FROM user WHERE id=:id" )
    fun findItemById(id: Int): LiveData<User>;

    @Query("SELECT * FROM user")
    fun getAllUser(): LiveData<List<User>>

    @Delete
    suspend fun deleteUser(user: User);
}