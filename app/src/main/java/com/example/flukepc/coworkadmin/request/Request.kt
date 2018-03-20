package com.example.flukepc.coworkadmin.request

import com.example.flukepc.coworkadmin.base.network.BaseService
import com.example.flukepc.coworkadmin.base.BaseSubScribe
import com.example.flukepc.coworkadmin.model.ListCoWork
import com.example.flukepc.coworkadmin.model.ResponseDataLogin
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

open class Request(private val api: BaseService) {

    private fun verifyLoginWithApi(email : String , password :String) = api.verifyLogin(email , password)
    private fun selectListCoWork()=api.requestCoWorkList()

    //todo change response type when integrate
    fun requestApiVerifyLogin(email : String , password :String , callback :BaseSubScribe.Response<ResponseDataLogin>){
        verifyLoginWithApi(email , password).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(BaseSubScribe(callback))
    }

    fun requestCoWorkList(callback: BaseSubScribe.Response<ListCoWork>){
        selectListCoWork().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(BaseSubScribe(callback))
    }
}