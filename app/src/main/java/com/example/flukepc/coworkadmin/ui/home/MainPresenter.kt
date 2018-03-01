package com.example.flukepc.coworkadmin.ui.home

import com.example.flukepc.coworkadmin.base.BasePresenter
import com.example.flukepc.coworkadmin.base.BaseSubScribe
import com.example.flukepc.coworkadmin.request.RequestLogin
import javax.inject.Inject

//todo Don't forget to change response type
class MainPresenter @Inject constructor(private val request: RequestLogin) : BasePresenter<MainContractor.View>()
        , MainContractor.Presenter
        , BaseSubScribe.Response<String> {

    override fun success(t: String) {
        getView()?.successLogin(t)
    }

    override fun validateForm(email: String, password: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun callApi(email: String, password: String) {
        request.requestApiVerifyLogin(email, password, this)
    }
}