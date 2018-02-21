@file:Suppress("DEPRECATION")

package com.example.flukepc.co_work_admin

import android.annotation.SuppressLint
import android.app.Application
import com.example.flukepc.co_work_admin.di.AndroidModule
import com.example.flukepc.co_work_admin.di.ApplicationComponent
import com.example.flukepc.co_work_admin.di.DaggerApplicationComponent
import com.example.flukepc.co_work_admin.di.network.ApiManager
import com.example.flukepc.co_work_admin.di.network.ApiModule

@SuppressLint("Registered")
class App : Application() {
    companion object {
        lateinit var appComponent: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.builder()
                .apiManager(ApiManager())
                .apiModule(ApiModule())
                .androidModule(AndroidModule(this))
                .build()
    }
}