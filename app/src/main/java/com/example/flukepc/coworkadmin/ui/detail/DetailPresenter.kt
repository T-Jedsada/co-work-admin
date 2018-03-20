package com.example.flukepc.coworkadmin.ui.detail

import com.example.flukepc.coworkadmin.base.BasePresenter
import com.example.flukepc.coworkadmin.base.BaseSubScribe
import com.example.flukepc.coworkadmin.model.ResponseDetail
import com.example.flukepc.coworkadmin.request.Request
import javax.inject.Inject

class DetailPresenter @Inject constructor(private val request: Request) : BasePresenter<DetailContact.View>(),
        DetailContact.Presenter, BaseSubScribe.Response<ResponseDetail>  {

    override fun success(t: ResponseDetail) {
        t.data?.let { getView()?.successDetail(it) }
    }


    override fun callApi(id: String) {
        request.requestDetail(id,this)
    }

}