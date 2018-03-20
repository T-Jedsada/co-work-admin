package com.example.flukepc.coworkadmin.ui.comment

import android.support.v7.widget.LinearLayoutManager
import com.example.flukepc.coworkadmin.R
import com.example.flukepc.coworkadmin.base.BaseActivity
import com.example.flukepc.coworkadmin.di.ApplicationComponent
import com.example.flukepc.coworkadmin.model.CommentList
import com.example.flukepc.coworkadmin.ui.comment.adapter.CommentAdapter
import kotlinx.android.synthetic.main.activity_comment.*

class CommentActivity : BaseActivity<CommentContact.View, CommentPresenter>(), CommentContact.View {
    private val commentAdapter: CommentAdapter by lazy { CommentAdapter(arrayListOf()) }

    override fun onCallCommentListSuccess(listComment: CommentList?) {
        commentAdapter.setItem(listComment?.data)
    }
    override fun layoutContentView(): Int = R.layout.activity_comment

    override fun doInjection(appComponent: ApplicationComponent) = appComponent.inject(this)

    override fun setupView() {
        commendList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = commentAdapter
        }
        val coWorkId = intent.getStringExtra("key")
        presenter.callCommentApi(coWorkId)
    }
    fun awoekfpwaoefk(){

    }
}