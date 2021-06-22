package com.android.cowork.admin.ui.comment

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.android.cowork.admin.R
import com.android.cowork.admin.base.BaseActivity
import com.android.cowork.admin.di.ApplicationComponent
import com.android.cowork.admin.getToast
import com.android.cowork.admin.model.CommentList
import com.android.cowork.admin.ui.comment.adapter.CommentAdapter
import com.android.cowork.admin.ui.home.HomeActivity
import kotlinx.android.synthetic.main.activity_comment.*

class CommentActivity : BaseActivity<CommentContact.View, CommentPresenter>(), CommentContact.View {

    private val commentAdapter: CommentAdapter by lazy { CommentAdapter(arrayListOf(), this) }
    private var coWorkId: String? = null
    private var commentId: String? = null

    override fun onError(message: Int) {
        this.getToast(getString(message))
    }

    override fun isDialogConfirm(option: Int?) {
        when (option) {
            OPTION_KEY_DELETE_COMMENT -> {
                loadDialog()
                presenter.isDeleteComment(commentId)
            }
            OPTION_KEY_EMPTY_LIST -> {
                startActivity(Intent(this, HomeActivity::class.java))
            }
        }
    }

    override fun onDeleteSuccess(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        coWorkId?.let {
            presenter.callCommentApi(it)
        } ?: Toast.makeText(this, getString(R.string.txt_interrupt), Toast.LENGTH_SHORT).show()
    }

    override fun onCommentDelete(id: String?) {
        commentId = id
        showDialog(getString(R.string.txt_for_sure), OPTION_KEY_DELETE_COMMENT)
    }

    override fun logIn(email: String?) {}

    override fun logOut() {}

    override fun checkSession() {}

    override fun onCallCommentListSuccess(listComment: CommentList?) {
        loadingSuccess()
        when (listComment?.data?.size == 0) {
            false -> commentAdapter.setItem(listComment?.data)
            true -> {
                tvEmptyList.visibility = View.VISIBLE
                showDialog(getString(R.string.txt_empty_list), OPTION_KEY_EMPTY_LIST)
            }
        }
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

    companion object {
        const val OPTION_KEY_EMPTY_LIST = 0
        const val OPTION_KEY_DELETE_COMMENT = 1
    }
}