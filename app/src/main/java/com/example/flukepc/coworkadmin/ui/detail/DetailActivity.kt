package com.example.flukepc.coworkadmin.ui.detail

import com.example.flukepc.coworkadmin.R
import com.example.flukepc.coworkadmin.base.BaseActivity
import com.example.flukepc.coworkadmin.di.ApplicationComponent
import com.example.flukepc.coworkadmin.load
import com.example.flukepc.coworkadmin.model.CoWorkDetail
import com.example.flukepc.coworkadmin.model.DataCoWorkDetail
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : BaseActivity<DetailContact.View, DetailPresenter>(), DetailContact.View {

    override fun isDialogConfirm() {}

    override fun logOut() {}

    override fun logIn(email: String?) {}

    override fun checkSession() {}

    companion object {
        const val Key = "KEY_DATA"
    }

    override fun layoutContentView(): Int = R.layout.activity_detail

    override fun doInjection(appComponent: ApplicationComponent) = appComponent.inject(this)

    override fun setupView() {
        val dataCoWork: CoWorkDetail = intent.getParcelableExtra(Key)
        nameCoWorking.text = dataCoWork.name
        address.text = (getString(R.string.txt_address) + dataCoWork.address)
        container.load(dataCoWork.gallery?.poster)
        status.text = (getString(R.string.txt_status) + dataCoWork.status.toString())
        dataCoWork.id?.let { presenter.callApi(it) }

    }

    override fun successDetail(data: List<DataCoWorkDetail>) {
        price.text = (getString(R.string.txt_price) + data[0].price_per_hour + getString(R.string.txt_bath))
        content.text = (getString(R.string.txt_content) + data[0].details)
    }
}