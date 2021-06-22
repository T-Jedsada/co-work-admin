package com.android.cowork.admin.ui.home.comment

import com.android.cowork.admin.R
import com.android.cowork.admin.base.BasePresenter
import com.android.cowork.admin.base.BaseSubScribe
import com.android.cowork.admin.model.ListCoWork
import com.android.cowork.admin.request.Request
import javax.inject.Inject

class CommentPresenter @Inject constructor(private val request: Request) : BasePresenter<CommentContact.View>(),
        CommentContact.Presenter, BaseSubScribe.Response<ListCoWork> {
    override fun onHttpError(message: Int) {
        getView()?.onError(message)
    }

    override fun success(t: ListCoWork) {
        when (t.results?.size != 0) {
            true -> getView()?.successCallback(t)
            false -> getView()?.onError(R.string.txt_api_error)
        }
    }

    override fun callListCoWorkApi() {
        request.requestCoWorkList(this)
    }
}