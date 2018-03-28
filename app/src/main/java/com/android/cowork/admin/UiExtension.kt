@file:Suppress("CAST_NEVER_SUCCEEDS", "NOTHING_TO_INLINE")

package com.android.cowork.admin

import android.content.Context
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import java.util.regex.Matcher
import java.util.regex.Pattern

infix fun ImageView.load(url: String?) = this.apply {
    com.bumptech.glide.Glide.with(context).load(url).into(this)
}

fun String?.emailPattern(): Matcher {
    val validEmail = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+"
    return Pattern.compile(validEmail).matcher(this)
}

fun Context.getToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

inline fun String?.showLog(logName: String) = Log.e(logName,this)