package com.android.cowork.admin.ui.home.comment

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.widget.Toast
import com.android.cowork.admin.R
import com.android.cowork.admin.base.BaseFragment
import com.android.cowork.admin.di.ApplicationComponent
import com.android.cowork.admin.model.ListCoWork
import com.android.cowork.admin.ui.home.comment.adapter.CommentAdapter
import kotlinx.android.synthetic.main.fragment_list_theme.*

class CommentFragment : BaseFragment<CommentContact.View, CommentPresenter>(), CommentContact.View {

    private val approveAdapter: CommentAdapter by lazy { CommentAdapter(arrayListOf()) }

    override fun onError(message: Int) {
        Toast.makeText(context, getString(message), Toast.LENGTH_SHORT).show()
    }

    override fun isDialogConfirm(id: String?, option: String?) {}

    override fun layoutInflate(): Int = R.layout.fragment_list_theme

    override fun doInjection(appComponent: ApplicationComponent) = appComponent.inject(this)

    override fun setAdapter() {
        presenter.callListCoWorkApi()
        coList.apply {
            layoutManager = LinearLayoutManager(context, OrientationHelper.VERTICAL, false)
            adapter = approveAdapter
        }
    }

    override fun initFunction() {
    }

    override fun successCallback(listCoWork: ListCoWork?) {
        approveAdapter.setItem(listCoWork?.results)
    }
}