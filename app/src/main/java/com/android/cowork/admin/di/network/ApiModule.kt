package com.android.cowork.admin.di.network

import com.android.cowork.admin.base.network.BaseService
import com.android.cowork.admin.request.Request
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
    fun provideGetData(userApi: BaseService): Request = Request(userApi)
}