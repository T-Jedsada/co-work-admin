package com.example.flukepc.coworkadmin.theme

import android.annotation.SuppressLint
import com.example.flukepc.coworkadmin.base.BaseActivity
import com.example.flukepc.coworkadmin.di.ApplicationComponent

@SuppressLint("Registered")
class ThemeActivity : BaseActivity<ThemeContact.View , ThemePresenter>(){
    override fun isDialogConfirm() {}

    override fun logIn(email: String?) {}

    override fun logOut() {}

    override fun checkSession() {}

    override fun layoutContentView(): Int {return 0}

    override fun doInjection(appComponent: ApplicationComponent) {}

    override fun setupView() {}
}