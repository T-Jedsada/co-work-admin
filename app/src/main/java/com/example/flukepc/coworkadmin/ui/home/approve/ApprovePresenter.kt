package com.example.flukepc.coworkadmin.ui.home.approve

import com.example.flukepc.coworkadmin.base.BasePresenter
import com.example.flukepc.coworkadmin.base.BaseSubScribe
import com.example.flukepc.coworkadmin.base.network.BaseService
import com.example.flukepc.coworkadmin.model.ListCoWork
import com.example.flukepc.coworkadmin.request.Request
import javax.inject.Inject

class ApprovePresenter @Inject constructor(private val request: Request): BasePresenter<ApproveContact.View>()
        , ApproveContact.Presenter ,BaseSubScribe.Response<ListCoWork>{
    override fun onApprove() {

    }

    override fun onReject() {

    }

    override fun success(t: ListCoWork) {
        getView()?.successCallback(t)
    }

    override fun callListCoWorkApi() {
        request.requestCoWorkList(this)
    }
}