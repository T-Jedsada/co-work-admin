package com.android.cowork.admin.ui.home

import com.android.cowork.admin.base.BasePresenter
import javax.inject.Inject

class HomePresenter @Inject constructor() : BasePresenter<HomeContact.View>(), HomeContact.Presenter {

    override fun callListCoWorkApi() {}
}