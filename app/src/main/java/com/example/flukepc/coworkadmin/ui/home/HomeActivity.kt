package com.example.flukepc.coworkadmin.ui.home

import android.annotation.SuppressLint
import com.example.flukepc.coworkadmin.R
import com.example.flukepc.coworkadmin.base.BaseActivity
import com.example.flukepc.coworkadmin.di.ApplicationComponent
import com.example.flukepc.coworkadmin.ui.home.adapter.Pager
import kotlinx.android.synthetic.main.activity_home.*

@SuppressLint("CommitPrefEdits")
class HomeActivity : BaseActivity<HomeContact.View, HomePresenter>() {

    private var pager: Pager? = null

    override fun isDialogConfirm() {
        logOut()
        finish()
    }

    override fun logIn(email: String?) {}

    override fun logOut() {
        session.logOut()
    }

    override fun checkSession() {}

    override fun layoutContentView(): Int = R.layout.activity_home

    override fun doInjection(appComponent: ApplicationComponent) = appComponent.inject(this)

    override fun setupView() {
        pager = Pager(supportFragmentManager)
        container.adapter = pager
        tabLayout?.setupWithViewPager(container)
    }

    override fun onBackPressed() {
        showDialog(getString(R.string.txt_confirm_logout))
    }

    companion object {
        const val tab1 = "Approve"
        const val tab2 = "CommentManager"
    }
}