package com.example.flukepc.coworkadmin.request

import com.example.flukepc.coworkadmin.base.BaseSubScribe
import com.example.flukepc.coworkadmin.base.network.BaseService
import com.example.flukepc.coworkadmin.model.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

open class Request(private val api: BaseService) {

    interface CommentListener {
        fun onDeleteCommentSuccess(callBack: ResponseJudgeComment)
    }

    private fun verifyLoginWithApi(email: String, password: String) = api.verifyLogin(email, password)
    private fun selectListCoWork() = api.requestCoWorkList()
    private fun selectDetail(id: String) = api.requestDetailCoWork(id)
    private fun selectCommentList(coWorkId: String) = api.requestCommentList(coWorkId)
    private fun deleteComment(id: String) = api.sendJudgementComment(id)
    private fun selectConfirmReject(id: String) = api.sendToConfirmReject(id)
    private fun selectConfirmApprove(id: String) = api.sendToConfirmApprove(id)

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
                .subscribe(BaseSubScribe(object : BaseSubScribe.Response<ResponseJudgeComment>{
                    override fun success(t: ResponseJudgeComment) {
                        callback.onDeleteCommentSuccess(t)
                    }
                }))
    }

    fun requestConfirmReject(id: String, callback: BaseSubScribe.Response<ResponseJudgeComment>) {
        selectConfirmReject(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(BaseSubScribe(callback))
    }

    fun requestConfirmApprove(id: String, callback: BaseSubScribe.Response<ResponseJudgeComment>) {
        selectConfirmApprove(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(BaseSubScribe(callback))
    }
}