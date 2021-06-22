package com.android.cowork.admin.request

import com.android.cowork.admin.base.BaseSubScribe
import com.android.cowork.admin.base.network.BaseService
import com.android.cowork.admin.model.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

open class Request(private val api: BaseService) {

    private fun verifyLoginWithApi(email: String, password: String) = api.verifyLogin(email, password)
    private fun selectListCoWork() = api.requestCoWorkList()
    private fun selectDetail(id: String) = api.requestDetailCoWork(id)
    private fun selectCommentList(coWorkId: String) = api.requestCommentList(coWorkId)
    private fun deleteComment(id: String) = api.sendJudgementComment(id)
    private fun selectConfirmReject(id: String) = api.sendToConfirmReject(id)
    private fun selectConfirmApprove(id: String) = api.sendToConfirmApprove(id)

    interface CommentListener {
        fun onDeleteCommentSuccess(callBack: JudgeResponse)
        fun onHttpError(message: Int)
    }

    interface JudgementCoWork {
        fun onActionSuccess(callback: JudgeResponse?)
        fun onHttpError(message: Int)
    }

    fun requestApiVerifyLogin(email: String, password: String, callback: BaseSubScribe.Response<ResponseDataLogin>) {
        verifyLoginWithApi(email, password).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(BaseSubScribe(callback))
    }

    fun requestCoWorkList(callback: BaseSubScribe.Response<ListCoWork>) {
        selectListCoWork().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(BaseSubScribe(callback))
    }

    fun requestDetail(id: String, callback: BaseSubScribe.Response<ResponseDetail>) {
        selectDetail(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(BaseSubScribe(callback))
    }

    fun requestCommentList(id: String, callback: BaseSubScribe.Response<CommentList>) {
        selectCommentList(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(BaseSubScribe(callback))
    }

    fun requestDeleteComment(id: String, callback: CommentListener) {
        deleteComment(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(BaseSubScribe(object : BaseSubScribe.Response<JudgeResponse> {
                    override fun onHttpError(message: Int) {
                        callback.onHttpError(message)
                    }

                    override fun success(t: JudgeResponse) {
                        callback.onDeleteCommentSuccess(t)
                    }
                }))
    }

    fun requestConfirmReject(id: String, callback: JudgementCoWork) {
        selectConfirmReject(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(BaseSubScribe(object : BaseSubScribe.Response<JudgeResponse> {
                    override fun onHttpError(message: Int) {
                        callback.onHttpError(message)
                    }

                    override fun success(t: JudgeResponse) {
                        callback.onActionSuccess(t)
                    }
                }))
    }

    fun requestConfirmApprove(id: String, callback: JudgementCoWork) {
        selectConfirmApprove(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(BaseSubScribe(object : BaseSubScribe.Response<JudgeResponse> {
                    override fun onHttpError(message: Int) {
                        callback.onHttpError(message)
                    }

                    override fun success(t: JudgeResponse) {
                        callback.onActionSuccess(t)
                    }
                }))
    }
}