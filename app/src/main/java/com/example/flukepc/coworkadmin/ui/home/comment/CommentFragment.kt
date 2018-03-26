package com.example.flukepc.coworkadmin.ui.home.comment

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import com.example.flukepc.coworkadmin.R
import com.example.flukepc.coworkadmin.base.BaseFragment
import com.example.flukepc.coworkadmin.di.ApplicationComponent
import com.example.flukepc.coworkadmin.model.ListCoWork
import com.example.flukepc.coworkadmin.ui.home.comment.adapter.CommentAdapter
import kotlinx.android.synthetic.main.fragment_list_theme.*

class CommentFragment : BaseFragment<CommentContact.View, CommentPresenter>(), CommentContact.View {
    private val approveAdapter: CommentAdapter by lazy { CommentAdapter(arrayListOf()) }

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