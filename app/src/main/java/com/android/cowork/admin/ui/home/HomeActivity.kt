package com.android.cowork.admin.ui.home

import android.annotation.SuppressLint
import com.android.cowork.admin.R
import com.android.cowork.admin.base.BaseActivity
import com.android.cowork.admin.di.ApplicationComponent
import com.android.cowork.admin.ui.home.adapter.Pager
import kotlinx.android.synthetic.main.activity_home.*

@SuppressLint("CommitPrefEdits")
class HomeActivity : BaseActivity<HomeContact.View, HomePresenter>() {

    private var pager: Pager? = null

    override fun isDialogConfirm(option: Int?) {
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