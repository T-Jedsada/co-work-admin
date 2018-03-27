package com.android.cowork.admin.ui.comment

import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.android.cowork.admin.R
import com.android.cowork.admin.base.BaseActivity
import com.android.cowork.admin.di.ApplicationComponent
import com.android.cowork.admin.model.CommentList
import com.android.cowork.admin.ui.comment.adapter.CommentAdapter
import kotlinx.android.synthetic.main.activity_comment.*

class CommentActivity : BaseActivity<CommentContact.View, CommentPresenter>(), CommentContact.View {

    private val commentAdapter: CommentAdapter by lazy { CommentAdapter(arrayListOf(), this) }
    private var coWorkId: String? = null
    private var commentId: String? = null

    override fun isDialogConfirm() {
        loadDialog()
        presenter.isDeleteComment(commentId)
    }

    override fun onDeleteSuccess(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        coWorkId?.let {
            presenter.callCommentApi(it)
        } ?: Toast.makeText(this, getString(R.string.txt_interupt), Toast.LENGTH_SHORT).show()
    }

    override fun onCommentDelete(id: String?) {
        commentId = id
        showDialog(getString(R.string.txt_for_sure))
    }

    override fun logIn(email: String?) {}

    override fun logOut() {}

    override fun checkSession() {}

    override fun onCallCommentListSuccess(listComment: CommentList?) {
        loadingSuccess()
        commentAdapter.setItem(listComment?.data)
    }

    override fun layoutContentView(): Int = R.layout.activity_comment

    override fun doInjection(appComponent: ApplicationComponent) = appComponent.inject(this)

    override fun setupView() {
        commendList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = commentAdapter
        }
        coWorkId = intent.getStringExtra("key")
        coWorkId?.let {
            loadDialog()
            presenter.callCommentApi(it)
        }
    }
}