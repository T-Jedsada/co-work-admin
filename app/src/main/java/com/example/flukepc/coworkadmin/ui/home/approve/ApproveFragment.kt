package com.example.flukepc.coworkadmin.ui.home.approve

import com.example.flukepc.coworkadmin.R
import com.example.flukepc.coworkadmin.base.BaseFragment
import com.example.flukepc.coworkadmin.di.ApplicationComponent

class ApproveFragment : BaseFragment<ApproveContact.View, ApprovePresenter>() {
    override fun layoutInflate(): Int = R.layout.fragment_approve

    override fun doInjection(appComponent: ApplicationComponent) = appComponent.inject(this)

    override fun setAdapter() {
    }

    override fun initFunction() {
    }
}