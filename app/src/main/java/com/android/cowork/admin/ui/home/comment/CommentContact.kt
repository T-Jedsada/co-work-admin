package com.android.cowork.admin.ui.home.comment

import com.android.cowork.admin.base.BaseContractor
import com.android.cowork.admin.model.ListCoWork

interface CommentContact {

    interface Presenter : BaseContractor.Presenter<View> {
        fun callListCoWorkApi()
    }

    interface View : BaseContractor.View {
        fun successCallback(listCoWork: ListCoWork?)
    }
}