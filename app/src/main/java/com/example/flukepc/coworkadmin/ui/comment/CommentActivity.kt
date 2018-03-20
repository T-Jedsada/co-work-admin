package com.example.flukepc.coworkadmin.ui.comment

import com.example.flukepc.coworkadmin.R
import com.example.flukepc.coworkadmin.base.BaseActivity
import com.example.flukepc.coworkadmin.di.ApplicationComponent
import com.example.flukepc.coworkadmin.ui.home.adapter.Pager

class CommentActivity : BaseActivity<CommentContact.View, CommentPresenter>() {
    private var pager: Pager? = null

    override fun layoutContentView(): Int = R.layout.activity_comment

    override fun doInjection(appComponent: ApplicationComponent) = appComponent.inject(this)

    override fun setupView() {
    }
}