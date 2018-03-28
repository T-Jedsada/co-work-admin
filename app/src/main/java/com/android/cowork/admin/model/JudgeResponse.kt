package com.android.cowork.admin.model

import com.google.gson.annotations.SerializedName

data class JudgeResponse(@SerializedName("success") val noticeMessage: String?,
                         @SerializedName("data") val data: JudgeResponseBody?)

data class JudgeResponseBody(@SerializedName("message") val message: String?,
                             @SerializedName("error") val error: String?)