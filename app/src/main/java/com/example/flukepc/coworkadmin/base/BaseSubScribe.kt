package com.example.flukepc.coworkadmin.base

import io.reactivex.observers.DisposableObserver
import retrofit2.Response

class BaseSubScribe<T>(private val callback: Response<T>) : DisposableObserver<Response<T>>() {
    override fun onComplete() {
        //TODO i dont need todo this task
    }

    override fun onNext(t: retrofit2.Response<T>) {
        t.body()?.let { callback.success(it) }
    }

    override fun onError(e: Throwable) {
        //todo return some error shower maybe toast
    }

    interface Response<in T> {
        fun success(t: T)
    }
}