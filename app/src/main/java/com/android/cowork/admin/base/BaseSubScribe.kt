package com.android.cowork.admin.base

import com.android.cowork.admin.R
import com.android.cowork.admin.showLog
import io.reactivex.observers.DisposableObserver
import retrofit2.Response

class BaseSubScribe<T>(private val callback: Response<T>) : DisposableObserver<Response<T>>() {

    override fun onComplete() {}

    override fun onNext(t: retrofit2.Response<T>) {
        when (t.code()) {
            200 -> t.body()?.let { callback.success(it) }
            404 -> {
                callback.onHttpError(R.string.http_wrong_connect_404)
            }
            401 -> {
                callback.onHttpError(R.string.http_wrong_connect_401)
            }
            500 -> {
                callback.onHttpError(R.string.http_wrong_connect_500)
            }
        }
    }

    override fun onError(e: Throwable) {
        callback.onHttpError(R.string.txt_api_error)
    }

    interface Response<in T> {
        fun success(t: T)
        fun onHttpError(message: Int)
    }
}