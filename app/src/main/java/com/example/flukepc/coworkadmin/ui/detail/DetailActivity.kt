package com.example.flukepc.coworkadmin.ui.detail

import com.example.flukepc.coworkadmin.R
import com.example.flukepc.coworkadmin.base.BaseActivity
import com.example.flukepc.coworkadmin.di.ApplicationComponent
import com.example.flukepc.coworkadmin.ui.home.adapter.Pager

class DetailActivity : BaseActivity<DetailContact.View, DetailPresenter>() {
    private var pager: Pager? = null

    override fun layoutContentView(): Int = R.layout.activity_detail

    override fun doInjection(appComponent: ApplicationComponent) = appComponent.inject(this)

    override fun setupView() {
    }
}