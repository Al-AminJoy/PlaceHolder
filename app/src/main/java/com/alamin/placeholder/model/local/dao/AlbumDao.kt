package com.alamin.placeholder.model.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.alamin.placeholder.model.data.Album

@Dao
interface AlbumDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun createAlbum(album: Album);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAlbumList(albumList: List<Album>);

    @Update
    suspend fun updateAlbum(album: Album);

    @Delete
    suspend fun deleteAlbum(album: Album);

    @Query("SELECT * FROM album")
    fun getAllAlbum():LiveData<List<Album>>

    @Query("SELECT * FROM album WHERE id=:id")
    fun findAlbumById(id: Int): LiveData<Album>
}