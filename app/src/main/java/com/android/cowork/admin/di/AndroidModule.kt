package com.android.cowork.admin.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.android.cowork.admin.App
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

    @Provides
    @Singleton
    fun provideSharePreference(): SharedPreferences = mApplication.getSharedPreferences("admin", Context.MODE_PRIVATE)
}