package com.alamin.placeholder.di

import androidx.lifecycle.ViewModel
import com.alamin.placeholder.view_model.AlbumViewModel
import com.alamin.placeholder.view_model.PhotoViewModel
import com.alamin.placeholder.view_model.PostViewModel
import com.alamin.placeholder.view_model.UserViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @ClassKey(UserViewModel::class)
    @IntoMap
    abstract fun provideUserViewModel(userViewModel: UserViewModel): ViewModel

    @Binds
    @ClassKey(PostViewModel::class)
    @IntoMap
    abstract fun providePostViewModel(postViewModel: PostViewModel): ViewModel

    @Binds
    @ClassKey(AlbumViewModel::class)
    @IntoMap
    abstract fun provideAlbumViewModel(albumViewModel: AlbumViewModel): ViewModel

    @Binds
    @ClassKey(PhotoViewModel::class)
    @IntoMap
    abstract fun providePhotoViewModel(photoViewModel: PhotoViewModel): ViewModel


}