package com.android.cowork.admin.ui.detail

import com.android.cowork.admin.base.BasePresenter
import com.android.cowork.admin.base.BaseSubScribe
import com.android.cowork.admin.model.ResponseDetail
import com.android.cowork.admin.request.Request
import javax.inject.Inject

class DetailPresenter @Inject constructor(private val request: Request) : BasePresenter<DetailContact.View>(),
        DetailContact.Presenter, BaseSubScribe.Response<ResponseDetail> {

    override fun success(t: ResponseDetail) {
        t.data?.let { getView()?.successDetail(it) }
    }

    override fun callApi(id: String) {
        request.requestDetail(id, this)
    }
}