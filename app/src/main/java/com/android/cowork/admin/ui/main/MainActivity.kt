package com.android.cowork.admin.ui.main

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.widget.Toast
import com.android.cowork.admin.R
import com.android.cowork.admin.base.BaseActivity
import com.android.cowork.admin.di.ApplicationComponent
import com.android.cowork.admin.getToast
import com.android.cowork.admin.model.ResponseDataLogin
import com.android.cowork.admin.ui.home.HomeActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@SuppressLint("Registered", "CommitPrefEdits")
class MainActivity : BaseActivity<MainContractor.View, MainPresenter>(), MainContractor.View {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun isDialogConfirm() {}

    override fun layoutContentView(): Int = R.layout.activity_main

    override fun doInjection(appComponent: ApplicationComponent) = appComponent.inject(this)

    override fun checkSession() {
        session.let {
            when (sharedPreferences.getBoolean(MainActivity.KEY_CHECK_LOGIN, false)) {
                true -> startActivity(Intent(this, HomeActivity::class.java))
            }
        }
    }

    override fun onError(message: Int) {
        loadingSuccess()
        this.getToast(getString(message))
    }

    override fun successLogin(data: ResponseDataLogin) {
        loadingSuccess()
        logIn(data.data?.email)
        startActivity(Intent(this, HomeActivity::class.java))
        Toast.makeText(applicationContext, getString(R.string.txt_success), Toast.LENGTH_LONG).show()
    }

    override fun successFormVerify(email: String, password: String) {
        presenter.callApi(email, password)
        loadDialog()
    }

    override fun setupView() {
        btnSubmit.setOnClickListener {
            presenter.validateForm(edtUsername.text?.trim().toString(), edtPass.text?.trim().toString())
        }
    }

    override fun logOut() {}

    override fun logIn(email: String?) {
        session.logIn(email)
    }

    companion object {
        const val KEY_SESSION_EMAIL = "session_email"
        const val KEY_CHECK_LOGIN = "lul"
    }
}