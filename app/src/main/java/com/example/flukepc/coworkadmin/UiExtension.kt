@file:Suppress("CAST_NEVER_SUCCEEDS")

package com.example.flukepc.coworkadmin

import android.widget.ImageView
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