package com.example.flukepc.coworkadmin.ui.home.approve

import com.example.flukepc.coworkadmin.base.BaseContractor
import com.example.flukepc.coworkadmin.model.ListCoWork

interface ApproveContact {

    interface Presenter : BaseContractor.Presenter<View> {
        fun callListCoWorkApi()
        fun onApprove(id: String?)
        fun onReject(id: String?)
    }

    interface View : BaseContractor.View {
        fun successCallback(listCoWork: ListCoWork?)
        fun onJudgeAction(id: String?, option: String?)
        fun isJudgeSuccess(message: String?)
    }
}