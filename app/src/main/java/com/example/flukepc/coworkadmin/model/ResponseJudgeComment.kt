package com.example.flukepc.coworkadmin.model

import com.google.gson.annotations.SerializedName

data class ResponseJudgeComment(@SerializedName("success") val noticeMessage: String?
                                , @SerializedName("data") val data: DataCoWorkJudgeComment?)

data class DataCoWorkJudgeComment(@SerializedName("message") val message: String?
                                  ,@SerializedName("error") val error: String?)