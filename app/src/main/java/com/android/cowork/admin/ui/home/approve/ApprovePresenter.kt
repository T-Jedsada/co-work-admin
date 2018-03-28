package com.android.cowork.admin.ui.home.approve

import com.android.cowork.admin.R
import com.android.cowork.admin.base.BasePresenter
import com.android.cowork.admin.base.BaseSubScribe
import com.android.cowork.admin.model.ListCoWork
import com.android.cowork.admin.model.JudgeResponse
import com.android.cowork.admin.request.Request
import javax.inject.Inject

class ApprovePresenter @Inject constructor(private val request: Request) : BasePresenter<ApproveContact.View>()
        , ApproveContact.Presenter, BaseSubScribe.Response<ListCoWork>, Request.JudgementCoWork {

    override fun onHttpError(message: Int) {
        getView()?.onError(message)
    }

    override fun onActionSuccess(callback: JudgeResponse?) {
        when (callback?.noticeMessage) {
            TRUE -> getView()?.isJudgeSuccess(callback.data?.message)
            FALSE -> getView()?.onError(R.string.txt_api_error)
        }
    }

    override fun onApprove(id: String?) {
        id?.let { request.requestConfirmApprove(it, this) }
    }

    override fun onReject(id: String?) {
        id?.let { request.requestConfirmReject(it, this) }
    }

    override fun success(t: ListCoWork) {
        (t.isSuccess).let { getView()?.successCallback(t) }?:getView()?.onError(R.string.txt_api_error)
    }

    override fun callListCoWorkApi() {
        request.requestCoWorkList(this)
    }

    companion object {
        const val TRUE = "true"
        const val FALSE = "false"
    }
}