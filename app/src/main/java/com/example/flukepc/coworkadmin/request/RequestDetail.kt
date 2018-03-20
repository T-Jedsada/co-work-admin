package com.example.flukepc.coworkadmin.request

import com.example.flukepc.coworkadmin.base.BaseSubScribe
import com.example.flukepc.coworkadmin.base.network.BaseService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class RequestDetail (private val api: BaseService) {

    private fun detailWithApi(id: String) = api.requestCoWorkDetail(id)

    //TODO Detail
    fun requestDetail(id : String, callback : BaseSubScribe.Response<RequestDetail>){
//        detailWithApi(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(BaseSubScribe(callback))
    }
}
