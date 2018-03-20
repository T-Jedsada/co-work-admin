package com.example.flukepc.coworkadmin.ui.detail

import com.example.flukepc.coworkadmin.base.BaseContractor

class DetailContact {
    interface Presenter : BaseContractor.Presenter<View> {
        fun checkIdProvider(id: String?)
    }

    interface View : BaseContractor.View {
        fun onResponseFromApi()
    }
}