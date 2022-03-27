package com.alamin.placeholder.model.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.alamin.placeholder.model.data.Photo

@Dao
interface PhotoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun createPhoto(photo: Photo);

    @Update
    suspend fun updatePhoto(photo: Photo);

    @Delete
    suspend fun deletePhoto(photo: Photo);

    @Query("SELECT * FROM photo")
    fun getAllPhoto(): LiveData<List<Photo>>

    @Query("SELECT * FROM photo WHERE id=:id")
    fun findPhotoById(id: Int): LiveData<Photo>
}