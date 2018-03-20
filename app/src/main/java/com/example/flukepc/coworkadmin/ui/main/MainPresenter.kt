package com.example.flukepc.coworkadmin.ui.main

import com.example.flukepc.coworkadmin.base.BasePresenter
import com.example.flukepc.coworkadmin.base.BaseSubScribe
import com.example.flukepc.coworkadmin.request.RequestLogin
import com.example.flukepc.coworkadmin.R
import com.example.flukepc.coworkadmin.emailPattern
import com.example.flukepc.coworkadmin.request.RequestDetail
import javax.inject.Inject

//todo Don't forget to change response type
class MainPresenter @Inject constructor(private val request: RequestLogin,
                                        private val requestDetail:RequestDetail) : BasePresenter<MainContractor.View>()
        , MainContractor.Presenter
        , BaseSubScribe.Response<String> {

    override fun success(t: String) {
        getView()?.successLogin(t)
    }

    override fun validateForm(email: String, password: String) {
        when{
            email.isEmpty() -> getView()?.onError(R.string.email_invalid)
            !email.emailPattern().matches() -> getView()?.onError(R.string.email_format_invalid)
            password.isEmpty() -> getView()?.onError(R.string.password_invalid)
            password.length > 30 -> getView()?.onError(R.string.password_longer)
            password.length < 6 -> getView()?.onError(R.string.password_shorter)
            else -> getView()?.successFormVerify(email,password)
        }
    }

    override fun callApi(email: String, password: String) {
        request.requestApiVerifyLogin(email, password, this)
    }

    //TODO Detail
    override fun callApiDetail(id: String) {
//        requestDetail.requestDetail(id,this)
    }


}