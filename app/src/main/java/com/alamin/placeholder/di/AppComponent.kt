package com.alamin.placeholder.di

import android.content.Context
import com.alamin.placeholder.view.activity.MainActivity
import com.alamin.placeholder.view.fragment.HomeFragment
import com.alamin.placeholder.view.fragment.UpdateFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, LocalDatabaseModule::class])
interface AppComponent {
    fun inject (mainActivity: MainActivity)
    fun injectHome(homeFragment: HomeFragment)
    fun injectUpdate(updateFragment: UpdateFragment)

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context): AppComponent
    }
}