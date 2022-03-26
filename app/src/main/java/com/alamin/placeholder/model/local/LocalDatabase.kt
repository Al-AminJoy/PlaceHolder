package com.alamin.placeholder.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alamin.placeholder.model.data.*
import com.alamin.placeholder.model.local.dao.*

@Database(entities = [User::class, Post::class, Comment::class, Album::class , Photo::class], version = 1, exportSchema = true)
abstract class LocalDatabase(): RoomDatabase() {
    abstract fun userDao(): UserDao;
    abstract fun postDao(): PostDao;
    abstract fun commentDao(): CommentDao;
    abstract fun albumDao(): AlbumDao;
    abstract fun photoDao(): PhotoDao;

    companion object{

        private var INSTANCE: LocalDatabase? =  null;

        fun getDatabase(context: Context): LocalDatabase {
            val tempInstance = INSTANCE;
            if (tempInstance != null){
                return tempInstance;
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LocalDatabase::class.java,
                    "local_database")
                    .build();
                INSTANCE = instance;
                return instance;
            }
        }
    }
}