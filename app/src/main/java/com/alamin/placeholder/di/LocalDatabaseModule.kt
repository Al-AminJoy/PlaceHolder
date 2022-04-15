package com.alamin.placeholder.di

import android.content.Context
import androidx.room.Room
import com.alamin.placeholder.model.local.LocalDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDatabaseModule {

    @Singleton
    @Provides
    fun provideLocalDatabase(context: Context): LocalDatabase{
        return Room.databaseBuilder(
            context.applicationContext,
            LocalDatabase::class.java,
            "local_database")
            .build()
    }

}