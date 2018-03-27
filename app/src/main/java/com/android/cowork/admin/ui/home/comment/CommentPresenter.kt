package com.android.cowork.admin.ui.home.comment

import com.android.cowork.admin.base.BasePresenter
import com.android.cowork.admin.base.BaseSubScribe
import com.android.cowork.admin.model.ListCoWork
import com.android.cowork.admin.request.Request
import javax.inject.Inject

class CommentPresenter @Inject constructor(private val request: Request) : BasePresenter<CommentContact.View>(),
        CommentContact.Presenter, BaseSubScribe.Response<ListCoWork> {

    override fun success(t: ListCoWork) {
        getView()?.successCallback(t)
    }

    override fun callListCoWorkApi() {
        request.requestCoWorkList(this)
    }
}