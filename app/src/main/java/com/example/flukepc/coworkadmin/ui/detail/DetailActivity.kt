package com.example.flukepc.coworkadmin.ui.detail

import com.example.flukepc.coworkadmin.R
import com.example.flukepc.coworkadmin.base.BaseActivity
import com.example.flukepc.coworkadmin.di.ApplicationComponent
import com.example.flukepc.coworkadmin.load
import com.example.flukepc.coworkadmin.model.CoWorkDetail
import com.example.flukepc.coworkadmin.model.DataCoWorkDetail
import com.example.flukepc.coworkadmin.ui.home.adapter.Pager
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : BaseActivity<DetailContact.View, DetailPresenter>(),DetailContact.View {
    override fun isDialogConfirm() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun logOut() {}

    override fun logIn(email: String?) {}

    override fun checkSession() {}

    private var pager: Pager? = null
    companion object { const val Key = "KEY_DATA" }

    override fun layoutContentView(): Int = R.layout.activity_detail

    override fun doInjection(appComponent: ApplicationComponent) = appComponent.inject(this)

    override fun setupView() {
        val dataCoWork: CoWorkDetail = intent.getParcelableExtra(Key)
        nameCoWorking.text=dataCoWork.name
        address.text=("address: "+dataCoWork.address)
        container.load(dataCoWork.gellery?.image_01)
        status.text= ("status: "+dataCoWork.status.toString())
        dataCoWork._id?.let { presenter.callApi(it) }

    }

    override fun successDetail(data: List<DataCoWorkDetail>) {
        price.text=("price: "+data.get(0).price_per_hour+" Bath")
        content.text=("content: "+data.get(0).details)
    }

}