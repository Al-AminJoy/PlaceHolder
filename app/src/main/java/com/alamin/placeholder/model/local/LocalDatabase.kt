package com.alamin.placeholder.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alamin.placeholder.model.data.User
import com.alamin.placeholder.model.local.dao.UserDao
@Database(entities = [User::class], version = 1, exportSchema = true)
abstract class LocalDatabase(): RoomDatabase() {
    abstract fun userDao(): UserDao;

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