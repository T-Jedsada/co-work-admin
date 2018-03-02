package com.example.flukepc.coworkadmin.base

import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface BaseService {

    //todo must be response something wait for update and add real path
    @POST("something")
    fun verifyLogin(@Body email: String, @Body password: String ): Observable<Response<String>>
}