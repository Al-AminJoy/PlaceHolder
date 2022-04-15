package com.alamin.placeholder

import android.app.Application
import com.alamin.placeholder.di.AppComponent
import com.alamin.placeholder.di.DaggerAppComponent

class PlaceHolderApplication: Application() {
lateinit var appComponent: AppComponent;
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
    }
}