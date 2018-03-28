package com.android.cowork.admin.util

import android.annotation.SuppressLint
import android.content.SharedPreferences
import com.android.cowork.admin.ui.main.MainActivity
import javax.inject.Inject

@SuppressLint("CommitPrefEdits")
open class Session @Inject constructor(private val sharedPreferences: SharedPreferences) {

    private val session: SharedPreferences.Editor? by lazy { sharedPreferences.edit() }

    fun logOut() {
        session?.putString(MainActivity.KEY_SESSION_EMAIL,null)
        session?.putBoolean(MainActivity.KEY_CHECK_LOGIN,false)
        session?.commit()
    }

    //TODO :: need to pass model of user to this method >> later
    fun logIn(email: String?) {
        session?.putString(MainActivity.KEY_SESSION_EMAIL, email)
        session?.putBoolean(MainActivity.KEY_CHECK_LOGIN, true)
        session?.commit()
    }
}