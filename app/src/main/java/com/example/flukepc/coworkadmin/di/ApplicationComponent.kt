package com.example.flukepc.coworkadmin.di

import com.example.flukepc.coworkadmin.ui.home.MainActivity
import com.example.flukepc.coworkadmin.di.network.ApiManager
import com.example.flukepc.coworkadmin.di.network.ApiModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(ApiManager::class), (ApiModule::class), (AndroidModule::class)])
interface ApplicationComponent {
    fun inject(mainActivity: MainActivity)
}