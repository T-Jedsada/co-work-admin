package com.example.flukepc.coworkadmin.ui.home.approve

import com.example.flukepc.coworkadmin.base.BasePresenter
import com.example.flukepc.coworkadmin.base.BaseSubScribe
import com.example.flukepc.coworkadmin.model.ListCoWork
import com.example.flukepc.coworkadmin.model.ResponseJudgeComment
import com.example.flukepc.coworkadmin.request.Request
import javax.inject.Inject

class ApprovePresenter @Inject constructor(private val request: Request) : BasePresenter<ApproveContact.View>()
        , ApproveContact.Presenter, BaseSubScribe.Response<ListCoWork>, Request.JudgementCoWork {

    override fun onActionSuccess(callback: ResponseJudgeComment?) {
        getView()?.isJudgeSuccess(callback?.data?.message)
    }

    override fun onApprove(id: String?) {
        id?.let { request.requestConfirmApprove(it, this) }
    }

    override fun onReject(id: String?) {
        id?.let { request.requestConfirmReject(it, this) }
    }

    override fun success(t: ListCoWork) {
        getView()?.successCallback(t)
    }

    override fun callListCoWorkApi() {
        request.requestCoWorkList(this)
    }
}