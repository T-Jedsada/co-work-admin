package com.example.flukepc.coworkadmin.ui.home.approve

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import com.example.flukepc.coworkadmin.R
import com.example.flukepc.coworkadmin.base.BaseFragment
import com.example.flukepc.coworkadmin.di.ApplicationComponent
import com.example.flukepc.coworkadmin.model.ListCoWork
import com.example.flukepc.coworkadmin.ui.home.approve.adapter.ApproveAdapter
import kotlinx.android.synthetic.main.fragment_list_theme.*

@Suppress("UNUSED_EXPRESSION")
class ApproveFragment : BaseFragment<ApproveContact.View, ApprovePresenter>(), ApproveContact.View {
    private val approveAdapter: ApproveAdapter by lazy { ApproveAdapter(arrayListOf(), this) }

    override fun isDialogConfirm(id: String?, option: String?) {
        loadDialog()
        when (option.equals(APPROVE_KEY_OPTION)) {
            true -> presenter.onApprove(id)
            false -> presenter.onReject(id)
        }
    }

    override fun isJudgeSuccess(message: String?) {
        presenter.callListCoWorkApi()
    }

    override fun successCallback(listCoWork: ListCoWork?) {
        loadingSuccess()
        approveAdapter.setItem(listCoWork?.results)
    }

    override fun onJudgeAction(id: String?, option: String?) {
        showDialog(getString(R.string.txt_for_sure), id, option)
    }

    override fun layoutInflate(): Int = R.layout.fragment_list_theme

    override fun doInjection(appComponent: ApplicationComponent) = appComponent.inject(this)

    override fun setAdapter() {
        loadDialog()
        presenter.callListCoWorkApi()
        coList.apply {
            layoutManager = LinearLayoutManager(context, OrientationHelper.VERTICAL, false)
            adapter = approveAdapter
        }
    }

    override fun initFunction() {}

    companion object {
        const val APPROVE_KEY_OPTION = "1"
        const val REJECT_KEY_OPTION = "2"
    }
}