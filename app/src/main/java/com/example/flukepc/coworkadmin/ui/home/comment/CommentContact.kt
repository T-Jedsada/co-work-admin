package com.example.flukepc.coworkadmin.ui.home.comment

import com.example.flukepc.coworkadmin.base.BaseContractor
import com.example.flukepc.coworkadmin.model.ListCoWork

interface CommentContact {
    interface Presenter : BaseContractor.Presenter<View> {
        fun callListCoWorkApi()
    }

    interface View : BaseContractor.View {
        fun successCallback(listCoWork: ListCoWork?)
    }
}