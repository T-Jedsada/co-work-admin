package com.example.flukepc.coworkadmin.base.network

import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.*

interface BaseService {

    //todo must be response something wait for update and add real path
    @FormUrlEncoded
    @POST("admin-login")
    fun verifyLogin(@Field("email") email: String, @Field("password") password: String ): Observable<Response<String>>

    @GET("")
    fun requestCoWorkData()

    @FormUrlEncoded
    @POST("")
    fun requestCommentList(@Field("coworking_id") coWorkingId : String )
}