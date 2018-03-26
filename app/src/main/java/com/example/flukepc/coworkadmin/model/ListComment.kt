package com.example.flukepc.coworkadmin.model

import com.google.gson.annotations.SerializedName


data class CommentList(@SerializedName("success") val noticeMessage: String?
                       , @SerializedName("data") val data: List<CommentData>?)

data class CommentData(@SerializedName("id") val id: String?,
                       @SerializedName("name") val name: String?,
                       @SerializedName("status") val status: String?,
                       @SerializedName("comment")val comment: String?)