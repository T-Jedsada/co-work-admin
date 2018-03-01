package com.example.flukepc.coworkadmin.ui.home

import android.annotation.SuppressLint
import com.example.flukepc.coworkadmin.R
import com.example.flukepc.coworkadmin.base.BaseActivity
import com.example.flukepc.coworkadmin.di.ApplicationComponent
import kotlinx.android.synthetic.main.activity_main.*

@SuppressLint("Registered")
class MainActivity : BaseActivity<MainContractor.View,MainPresenter>(),MainContractor.View {
    override fun onError(message: String) {
        //todo some thing if got error
    }

    override fun successLogin(message: String) {
        //todo some thing if login success
    }

    override fun successFormVerify(email: String, password: String) {
        //presenter.callApi(email , password)
    }

    override fun layoutContentView(): Int = R.layout.activity_main

    override fun doInjection(appComponent: ApplicationComponent)=appComponent.inject(this)

    override fun setupView() {
        btnSubmit.setOnClickListener {
           // presenter.validateForm(edtUsername.text.trim().toString(),edtPass.text.trim().toString())
        }
    }
}