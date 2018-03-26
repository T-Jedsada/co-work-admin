package com.example.flukepc.coworkadmin.base.network

import com.example.flukepc.coworkadmin.model.*
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.*

interface BaseService {

    //todo must be response something wait for update and add real path
    @FormUrlEncoded
    @POST("admin-login")
    fun verifyLogin(@Field("email") email: String, @Field("password") password: String ): Observable<Response<ResponseDataLogin>>

    @GET("admin/co-work-list/")
    fun requestCoWorkList():Observable<Response<ListCoWork>>

    @FormUrlEncoded
    @POST("admin/show-comment/")
    fun requestCommentList(@Field("id") coWorkingId : String ):Observable<Response<CommentList>>

    @FormUrlEncoded
    @POST("admin/approve/")
    fun sendToConfirmApprove(@Field("co_work_id") coWorkingId : String):Observable<Response<ResponseJudgeComment>>

    @FormUrlEncoded
    @POST("admin/judgement-cowork/")
    fun sendToConfirmReject(@Field("co_work_id") coWorkingId : String):Observable<Response<ResponseJudgeComment>>

    @FormUrlEncoded
    @POST("admin/judge-comment/")
    fun sendJudgementComment(@Field("id") commentId : String):Observable<Response<ResponseJudgeComment>>

    //TODO onCallDetailCoWork
    @FormUrlEncoded
    @POST("detail-cowork")
    fun requestDetailCoWork(@Field("id") id: String?
    ): Observable<Response<ResponseDetail>>
}