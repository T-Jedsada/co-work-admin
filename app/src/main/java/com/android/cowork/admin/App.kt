@file:Suppress("DEPRECATION")

package com.android.cowork.admin

import android.annotation.SuppressLint
import android.app.Application
import com.android.cowork.admin.di.AndroidModule
import com.android.cowork.admin.di.ApplicationComponent
import com.android.cowork.admin.di.DaggerApplicationComponent
import com.android.cowork.admin.di.network.ApiManager
import com.android.cowork.admin.di.network.ApiModule

@SuppressLint("Registered")
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.builder()
                .apiManager(ApiManager())
                .apiModule(ApiModule())
                .androidModule(AndroidModule(this))
                .build()
    }

    companion object {
        lateinit var appComponent: ApplicationComponent
    }
}