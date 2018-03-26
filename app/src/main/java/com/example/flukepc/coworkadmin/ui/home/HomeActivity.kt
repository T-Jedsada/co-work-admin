package com.example.flukepc.coworkadmin.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import com.example.flukepc.coworkadmin.R
import com.example.flukepc.coworkadmin.base.BaseActivity
import com.example.flukepc.coworkadmin.di.ApplicationComponent
import com.example.flukepc.coworkadmin.ui.home.adapter.Pager
import com.example.flukepc.coworkadmin.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

@SuppressLint("CommitPrefEdits")
class HomeActivity : BaseActivity<HomeContact.View, HomePresenter>() {
    override fun isDialogConfirm() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    private val session: SharedPreferences.Editor? by lazy { sharedPreferences.edit() }

    override fun logIn(email: String?) {}

    override fun logOut() {
        session?.clear()
        session?.putBoolean(MainActivity.KEY_CHECK_LOGIN, false)
    }

    override fun checkSession() {}

    private var pager: Pager? = null

    override fun layoutContentView(): Int = R.layout.activity_home

    override fun doInjection(appComponent: ApplicationComponent) = appComponent.inject(this)

    override fun setupView() {
        pager = Pager(supportFragmentManager)
        container.adapter = pager
        tabLayout?.setupWithViewPager(container)
    }

    override fun onBackPressed() {
        logOut()
        startActivity(Intent(this, MainActivity::class.java))
    }

    companion object {
        const val tab1 = "Approve"
        const val tab2 = "CommentManager"
    }
}