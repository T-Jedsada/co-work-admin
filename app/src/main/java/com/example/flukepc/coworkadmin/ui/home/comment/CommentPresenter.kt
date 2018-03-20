package com.example.flukepc.coworkadmin.ui.home.comment

import com.example.flukepc.coworkadmin.base.BasePresenter
import com.example.flukepc.coworkadmin.base.BaseSubScribe
import com.example.flukepc.coworkadmin.model.ListCoWork
import com.example.flukepc.coworkadmin.request.Request
import javax.inject.Inject

class CommentPresenter @Inject constructor(private val request: Request) : BasePresenter<CommentContact.View>(),
        CommentContact.Presenter, BaseSubScribe.Response<ListCoWork>{

    override fun success(t: ListCoWork) {
        getView()?.successCallback(t)
    }

    override fun callListCoWorkApi() {
        request.requestCoWorkList(this)
    }

}