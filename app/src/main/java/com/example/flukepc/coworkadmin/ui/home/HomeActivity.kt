package com.example.flukepc.coworkadmin.ui.home

import com.example.flukepc.coworkadmin.R
import com.example.flukepc.coworkadmin.base.BaseActivity
import com.example.flukepc.coworkadmin.di.ApplicationComponent
import com.example.flukepc.coworkadmin.ui.home.adapter.Pager
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity<HomeContact.View, HomePresenter>() {
    private var pager: Pager? = null

    override fun layoutContentView(): Int = R.layout.activity_home

    override fun doInjection(appComponent: ApplicationComponent) = appComponent.inject(this)

    override fun setupView() {
        pager = Pager(supportFragmentManager)
        container.adapter = pager
        tabLayout?.setupWithViewPager(container)
    }

    companion object {
        const val tab1 = "Approve"
        const val tab2 = "CommentManager"
    }
}