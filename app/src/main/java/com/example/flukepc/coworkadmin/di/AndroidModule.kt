package com.example.flukepc.coworkadmin.di

import android.app.Application
import android.content.Context
import com.example.flukepc.coworkadmin.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AndroidModule(private var mApplication: App) {
    @Provides
    @Singleton
    fun provideApplication(): Application = mApplication

    @Provides
    @Singleton
    fun provideContext(): Context = mApplication.applicationContext
}