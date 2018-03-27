@file:Suppress("DEPRECATION")

package com.example.flukepc.coworkadmin

import android.annotation.SuppressLint
import android.app.Application
import com.example.flukepc.coworkadmin.di.AndroidModule
import com.example.flukepc.coworkadmin.di.ApplicationComponent
import com.example.flukepc.coworkadmin.di.DaggerApplicationComponent
import com.example.flukepc.coworkadmin.di.network.ApiManager
import com.example.flukepc.coworkadmin.di.network.ApiModule

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