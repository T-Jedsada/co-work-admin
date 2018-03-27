package com.android.cowork.admin.ui.main

import com.android.cowork.admin.base.BaseContractor
import com.android.cowork.admin.model.ResponseDataLogin

interface MainContractor : BaseContractor.View {

    interface Presenter : BaseContractor.Presenter<View> {
        fun validateForm(email: String, password: String)
        fun callApi(email: String, password: String)
    }

    interface View : BaseContractor.View {
        fun successFormVerify(email: String, password: String)
        fun successLogin(data: ResponseDataLogin)
        fun onError(message: Int)
    }
}