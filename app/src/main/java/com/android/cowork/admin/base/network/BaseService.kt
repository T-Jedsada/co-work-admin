package com.android.cowork.admin.base.network

import com.android.cowork.admin.model.*
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface BaseService {

    @FormUrlEncoded
    @POST("admin-login")
    fun verifyLogin(@Field("email") email: String, @Field("password") password: String)
            : Observable<Response<ResponseDataLogin>>

    @GET("admin/co-work-list/")
    fun requestCoWorkList(): Observable<Response<ListCoWork>>

    @FormUrlEncoded
    @POST("admin/show-comment/")
    fun requestCommentList(@Field("id") coWorkingId: String): Observable<Response<CommentList>>

    @FormUrlEncoded
    @POST("admin/approve/")
    fun sendToConfirmApprove(@Field("co_work_id") coWorkingId: String): Observable<Response<JudgeResponse>>

    @FormUrlEncoded
    @POST("admin/judgement-cowork/")
    fun sendToConfirmReject(@Field("co_work_id") coWorkingId: String): Observable<Response<JudgeResponse>>

    @FormUrlEncoded
    @POST("admin/judge-comment/")
    fun sendJudgementComment(@Field("id") commentId: String): Observable<Response<JudgeResponse>>

    @FormUrlEncoded
    @POST("detail-cowork")
    fun requestDetailCoWork(@Field("id") id: String?): Observable<Response<ResponseDetail>>
}