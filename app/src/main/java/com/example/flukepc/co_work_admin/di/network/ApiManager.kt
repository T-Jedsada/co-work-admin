package com.example.flukepc.co_work_admin.di.network

import android.app.Application
import com.example.flukepc.co_work_admin.base.BaseUrl
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class ApiManager {
    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder()
            .setLenient()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create()

    @Provides
    @Singleton
    fun provideRetroReactiveX(gson: Gson, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BaseUrl.baseUrl)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideOkHttpClientBuilder(): OkHttpClient.Builder = OkHttpClient.Builder()

    @Provides
    @Singleton
    fun provideHttpCache(application: Application): Cache =
            Cache(application.cacheDir, (10 * 1024 * 1024).toLong())

    @Provides
    fun provideHttpLogger(): HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)


    @Provides
    @Singleton
    fun provideOkHttpClient(logger: HttpLoggingInterceptor): OkHttpClient {
        val okHttpBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
                .addInterceptor(logger)
                .addInterceptor { chain ->
                    val original = chain.request()
                    val originalHttpUrl = original.url()
                    val url = originalHttpUrl.newBuilder()
                            //todo insert some api_key here
                            .addQueryParameter("api_key", "")
                            .build()
                    val requestBuilder = original.newBuilder()
                            .url(url)
                    val request = requestBuilder.build()
                    return@addInterceptor chain.proceed(request)
                }
        return okHttpBuilder.build()
    }
}