package com.example.flukepc.coworkadmin.ui.detail

import com.example.flukepc.coworkadmin.base.BaseContractor
import com.example.flukepc.coworkadmin.model.DataCoWorkDetail

class DetailContact {
    interface Presenter : BaseContractor.Presenter<View> {
        fun callApi(id: String)
    }

    interface View : BaseContractor.View {
        fun successDetail(data: List<DataCoWorkDetail>)
    }
}