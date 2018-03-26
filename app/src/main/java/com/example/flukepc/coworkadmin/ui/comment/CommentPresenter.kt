package com.example.flukepc.coworkadmin.ui.comment

import com.example.flukepc.coworkadmin.base.BasePresenter
import com.example.flukepc.coworkadmin.base.BaseSubScribe
import com.example.flukepc.coworkadmin.model.CommentList
import com.example.flukepc.coworkadmin.model.ResponseJudgeComment
import com.example.flukepc.coworkadmin.request.Request
import javax.inject.Inject

class CommentPresenter @Inject constructor(private val request: Request) : BasePresenter<CommentContact.View>()
        , CommentContact.Presenter, BaseSubScribe.Response<CommentList>, Request.CommentListener {

    override fun onDeleteCommentSuccess(callBack: ResponseJudgeComment) {
        callBack.data?.let { getView()?.onDeleteSuccess(it.message) }
    }

    override fun isDeleteComment(id: String?) {
        id?.let { request.requestDeleteComment(it, this) }
    }

    override fun callCommentApi(coWorkingId: String) = request.requestCommentList(coWorkingId, this)

    override fun success(t: CommentList) {
        getView()?.onCallCommentListSuccess(t)
    }
}