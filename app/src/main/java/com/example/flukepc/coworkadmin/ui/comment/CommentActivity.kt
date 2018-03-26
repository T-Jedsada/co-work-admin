package com.example.flukepc.coworkadmin.ui.comment

import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.flukepc.coworkadmin.R
import com.example.flukepc.coworkadmin.base.BaseActivity
import com.example.flukepc.coworkadmin.di.ApplicationComponent
import com.example.flukepc.coworkadmin.model.CommentList
import com.example.flukepc.coworkadmin.ui.comment.adapter.CommentAdapter
import kotlinx.android.synthetic.main.activity_comment.*

class CommentActivity : BaseActivity<CommentContact.View, CommentPresenter>(), CommentContact.View {

    var coWorkId: String? = null
    var commentId: String? = null

    override fun isDialogConfirm() {
        presenter.isDeleteComment(commentId)
    }

    override fun onDeleteSuccess(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        coWorkId?.let {
            loadDialog()
            presenter.callCommentApi(it)
        }
                ?: Toast.makeText(this, getString(R.string.txt_interupt), Toast.LENGTH_SHORT).show()
    }

    override fun onCommentDelete(id: String?) {
        commentId = id
        showDialog(getString(R.string.txt_for_sure))
    }

    override fun logIn(email: String?) {}

    override fun logOut() {}

    override fun checkSession() {}

    private val commentAdapter: CommentAdapter by lazy { CommentAdapter(arrayListOf(), this) }

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