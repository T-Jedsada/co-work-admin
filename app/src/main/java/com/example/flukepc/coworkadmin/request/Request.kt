package com.example.flukepc.coworkadmin.request

import com.example.flukepc.coworkadmin.base.network.BaseService
import com.example.flukepc.coworkadmin.base.BaseSubScribe
import com.example.flukepc.coworkadmin.model.ListCoWork
import com.example.flukepc.coworkadmin.model.ResponseDataLogin
import com.example.flukepc.coworkadmin.model.ResponseDetail
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

open class Request(private val api: BaseService) {

    private fun verifyLoginWithApi(email : String , password :String) = api.verifyLogin(email , password)
    private fun selectListCoWork()=api.requestCoWorkList()
    private fun selectDetail(id:String)=api.requestDetailCoWork(id)


    //todo change response type when integrate
    fun requestApiVerifyLogin(email : String , password :String , callback :BaseSubScribe.Response<ResponseDataLogin>){
        verifyLoginWithApi(email , password).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(BaseSubScribe(callback))
    }

    fun requestCoWorkList(callback: BaseSubScribe.Response<ListCoWork>){
        selectListCoWork().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(BaseSubScribe(callback))
    }

    //TODO
    fun  requestDetail(id: String , callback :BaseSubScribe.Response<ResponseDetail>){
        selectDetail(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(BaseSubScribe(callback))
    }
}