package com.example.flukepc.coworkadmin.ui.home.comment

import com.example.flukepc.coworkadmin.R
import com.example.flukepc.coworkadmin.base.BaseFragment
import com.example.flukepc.coworkadmin.di.ApplicationComponent

class CommentFragment : BaseFragment<CommentContact.View, CommentPresenter>() {
    override fun layoutInflate(): Int = R.layout.fragment_list_theme

    override fun doInjection(appComponent: ApplicationComponent) = appComponent.inject(this)

    override fun setAdapter() {
    }

    override fun initFunction() {
    }
}