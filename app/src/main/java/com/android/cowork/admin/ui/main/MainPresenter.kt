package com.android.cowork.admin.ui.main

import com.android.cowork.admin.R
import com.android.cowork.admin.base.BasePresenter
import com.android.cowork.admin.base.BaseSubScribe
import com.android.cowork.admin.emailPattern
import com.android.cowork.admin.model.ResponseDataLogin
import com.android.cowork.admin.request.Request
import javax.inject.Inject

class MainPresenter @Inject constructor(private val request: Request) : BasePresenter<MainContractor.View>()
        , MainContractor.Presenter
        , BaseSubScribe.Response<ResponseDataLogin> {

    override fun onHttpError(message: Int) {
        getView()?.onError(message)
    }

    override fun success(t: ResponseDataLogin) {
        when (t.noticeMessage) {
            TRUE -> t.let { getView()?.successLogin(it) }
            FALSE -> getView()?.onError(R.string.txt_api_error)
        }
    }

    override fun validateForm(email: String, password: String) {
        when {
            email.isEmpty() -> getView()?.onError(R.string.email_invalid)
            !email.emailPattern().matches() -> getView()?.onError(R.string.email_format_invalid)
            password.isEmpty() -> getView()?.onError(R.string.password_invalid)
            password.length > 30 -> getView()?.onError(R.string.password_longer)
            password.length < 6 -> getView()?.onError(R.string.password_shorter)
            else -> getView()?.successFormVerify(email, password)
        }
    }

    override fun callApi(email: String, password: String) =
            request.requestApiVerifyLogin(email, password, this)

    companion object {
        const val TRUE = "true"
        const val FALSE = "false"
    }
}