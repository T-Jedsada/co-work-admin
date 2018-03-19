package com.example.flukepc.coworkadmin.request

import com.example.flukepc.coworkadmin.base.network.BaseService
import com.example.flukepc.coworkadmin.base.BaseSubScribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

open class RequestLogin(private val api: BaseService) {

    private fun verifyLoginWithApi(email : String , password :String) = api.verifyLogin(email , password)

    //todo change response type when integrate
    fun requestApiVerifyLogin(email : String , password :String , callback :BaseSubScribe.Response<String>){
        verifyLoginWithApi(email , password).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(BaseSubScribe(callback))
    }
}