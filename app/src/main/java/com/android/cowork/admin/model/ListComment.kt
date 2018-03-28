package com.android.cowork.admin.model

import com.google.gson.annotations.SerializedName


data class CommentList(@SerializedName("success") val noticeMessage: String?
                       , @SerializedName("data") val data: List<CommentData>?)

data class CommentData(@SerializedName("_id") val id: String?,
                       @SerializedName("user_id") val user_id: String?,
                       @SerializedName("status") val status: String?,
                       @SerializedName("comment") val comment: String?,
                       @SerializedName("name") val name: String?)