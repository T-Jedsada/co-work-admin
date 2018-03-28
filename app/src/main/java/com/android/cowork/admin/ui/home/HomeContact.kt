package com.android.cowork.admin.ui.home

import com.android.cowork.admin.base.BaseContractor

class HomeContact {

    interface Presenter : BaseContractor.Presenter<View> {
        fun callListCoWorkApi()
    }

    interface View : BaseContractor.View
}