package com.android.cowork.admin.theme

import android.annotation.SuppressLint
import com.android.cowork.admin.base.BaseActivity
import com.android.cowork.admin.di.ApplicationComponent

@SuppressLint("Registered")
class ThemeActivity : BaseActivity<ThemeContact.View , ThemePresenter>(), ThemeContact.View{
    override fun onError(message: Int) {}

    override fun isDialogConfirm() {}

    override fun logIn(email: String?) {}

    override fun logOut() {}

    override fun checkSession() {}

    override fun layoutContentView(): Int {return 0}

    override fun doInjection(appComponent: ApplicationComponent) {}

    override fun setupView() {}
}