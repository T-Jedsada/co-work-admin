package com.example.flukepc.coworkadmin.ui.confirm

import com.example.flukepc.coworkadmin.base.BasePresenter
import com.example.flukepc.coworkadmin.base.BaseSubScribe
import com.example.flukepc.coworkadmin.model.ResponseJudgeComment
import com.example.flukepc.coworkadmin.request.Request
import javax.inject.Inject

class  ConfirmPresenter@Inject constructor(private val request: Request) : BasePresenter<ConfirmContact.View>(),
        ConfirmContact.Presenter,
        BaseSubScribe.Response<ResponseJudgeComment>  {

    override fun success(t: ResponseJudgeComment) {
        t.data?.let { getView()?.successApiRejectComment(it) }
    }

    override fun callApiRejectComment(id: String) {
        request.requestConfirm(id, this)
    }

    override fun callApiRejectCoWork(id: String) {
        request.requestConfirmReject(id, this)
    }

    override fun callApiApproveCoWork(id: String) {
        request.requestConfirmApprove(id, this)
    }

}