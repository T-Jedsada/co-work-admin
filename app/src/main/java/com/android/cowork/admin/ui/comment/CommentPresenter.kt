package com.android.cowork.admin.ui.comment

import com.android.cowork.admin.R
import com.android.cowork.admin.base.BasePresenter
import com.android.cowork.admin.base.BaseSubScribe
import com.android.cowork.admin.model.CommentList
import com.android.cowork.admin.model.ResponseJudgeComment
import com.android.cowork.admin.request.Request
import javax.inject.Inject

class CommentPresenter @Inject constructor(private val request: Request) : BasePresenter<CommentContact.View>()
        , CommentContact.Presenter, BaseSubScribe.Response<CommentList>, Request.CommentListener {

    override fun onHttpError(message: Int) {
        getView()?.onError(message)
    }

    override fun onDeleteCommentSuccess(callBack: ResponseJudgeComment) {
        when (callBack.noticeMessage) {
            TRUE -> callBack.data?.let { getView()?.onDeleteSuccess(it.message) }
            FALSE -> getView()?.onError(R.string.txt_api_error)
        }
    }

    override fun isDeleteComment(id: String?) {
        id?.let { request.requestDeleteComment(it, this) }
    }

    override fun callCommentApi(coWorkingId: String) = request.requestCommentList(coWorkingId, this)

    override fun success(t: CommentList) {
        getView()?.onCallCommentListSuccess(t)
    }

    companion object {
        const val TRUE = "true"
        const val FALSE = "false"
    }
}