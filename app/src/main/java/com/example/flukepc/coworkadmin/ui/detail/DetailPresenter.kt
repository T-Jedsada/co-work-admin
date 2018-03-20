package com.example.flukepc.coworkadmin.ui.detail

import com.example.flukepc.coworkadmin.base.BasePresenter
import javax.inject.Inject

class DetailPresenter @Inject constructor() : BasePresenter<DetailContact.View>(), DetailContact.Presenter {
    override fun checkIdProvider(id: String?) {}

}