package com.android.cowork.admin.base

open class BaseContractor {

    interface View

    interface Presenter<V : View> {
        fun attachView(view: V)
        fun getView(): V?
    }
}