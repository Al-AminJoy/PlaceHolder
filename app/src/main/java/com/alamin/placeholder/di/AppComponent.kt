package com.alamin.placeholder.di

import android.content.Context
import com.alamin.placeholder.view.activity.LoginActivity
import com.alamin.placeholder.view.activity.MainActivity
import com.alamin.placeholder.view.fragment.*
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, LocalDatabaseModule::class])
interface AppComponent {
    fun injectMain (mainActivity: MainActivity)
    fun injectLogin(loginActivity: LoginActivity)
    fun injectHome(homeFragment: HomeFragment)
    fun injectUpdate(updateFragment: UpdateFragment)
    fun injectAlbum(albumFragment: AlbumFragment)
    fun injectPhoto(photoFragment: PhotoFragment)
    fun injectCreatePost(createPostFragment: CreatePostFragment)

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context): AppComponent
    }
}