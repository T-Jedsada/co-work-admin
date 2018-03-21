package com.example.flukepc.coworkadmin.ui.home

import com.example.flukepc.coworkadmin.base.BaseContractor

class HomeContact {
    interface Presenter : BaseContractor.Presenter<View> {
        fun callListCoWorkApi()
    }

    interface View : BaseContractor.View {
        fun successlistCoWorkApi()
    }
}