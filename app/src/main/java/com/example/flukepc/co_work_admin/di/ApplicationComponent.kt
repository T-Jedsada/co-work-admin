package com.example.flukepc.co_work_admin.di

import com.example.flukepc.co_work_admin.MainActivity
import com.example.flukepc.co_work_admin.di.network.ApiManager
import com.example.flukepc.co_work_admin.di.network.ApiModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(ApiManager::class), (ApiModule::class), (AndroidModule::class)])
interface ApplicationComponent {
    fun inject(mainActivity: MainActivity)

}