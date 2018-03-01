package com.example.flukepc.coworkadmin.di.network

import com.example.flukepc.coworkadmin.request.RequestLogin
import com.example.flukepc.coworkadmin.base.BaseService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideUserApi(retrofit: Retrofit): BaseService = retrofit.create(BaseService::class.java)

    @Provides
    @Singleton
    fun provideGetData(userApi: BaseService): RequestLogin = RequestLogin(userApi)
}