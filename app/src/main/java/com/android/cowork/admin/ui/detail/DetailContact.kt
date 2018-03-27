package com.android.cowork.admin.ui.detail

import com.android.cowork.admin.base.BaseContractor
import com.android.cowork.admin.model.DataCoWorkDetail

class DetailContact {

    interface Presenter : BaseContractor.Presenter<View> {
        fun callApi(id: String)
    }

    interface View : BaseContractor.View {
        fun successDetail(data: List<DataCoWorkDetail>)
    }
}