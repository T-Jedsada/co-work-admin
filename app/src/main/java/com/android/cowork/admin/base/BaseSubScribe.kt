package com.android.cowork.admin.base

import android.util.Log
import io.reactivex.observers.DisposableObserver
import retrofit2.Response

class BaseSubScribe<T>(private val callback: Response<T>) : DisposableObserver<Response<T>>() {

    override fun onComplete() {}

    override fun onNext(t: retrofit2.Response<T>) {
        t.body()?.let { callback.success(it) }
    }

    override fun onError(e: Throwable) {
        Log.e("Error : ", e.message)
    }

    interface Response<in T> {
        fun success(t: T)
    }
}