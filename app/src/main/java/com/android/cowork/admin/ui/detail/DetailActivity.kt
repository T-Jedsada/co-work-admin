package com.android.cowork.admin.ui.detail

import com.android.cowork.admin.R
import com.android.cowork.admin.base.BaseActivity
import com.android.cowork.admin.di.ApplicationComponent
import com.android.cowork.admin.load
import com.android.cowork.admin.model.CoWorkDetail
import com.android.cowork.admin.model.DataCoWorkDetail
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : BaseActivity<DetailContact.View, DetailPresenter>(), DetailContact.View {

    override fun isDialogConfirm() {}

    override fun logOut() {}

    override fun logIn(email: String?) {}

    override fun checkSession() {}

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

    companion object {
        const val Key = "KEY_DATA"
    }
}