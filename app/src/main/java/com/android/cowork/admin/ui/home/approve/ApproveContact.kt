package com.android.cowork.admin.ui.home.approve

import com.android.cowork.admin.base.BaseContractor
import com.android.cowork.admin.model.ListCoWork

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
        fun onError(message: Int)
    }
}