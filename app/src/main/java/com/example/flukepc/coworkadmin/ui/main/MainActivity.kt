package com.example.flukepc.coworkadmin.ui.main

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.widget.Toast
import com.example.flukepc.coworkadmin.R
import com.example.flukepc.coworkadmin.base.BaseActivity
import com.example.flukepc.coworkadmin.di.ApplicationComponent
import com.example.flukepc.coworkadmin.model.DataLogin
import com.example.flukepc.coworkadmin.ui.home.HomeActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@SuppressLint("Registered", "CommitPrefEdits")
class MainActivity : BaseActivity<MainContractor.View, MainPresenter>(), MainContractor.View {
    @Inject
    lateinit var sharedPreferences: SharedPreferences

    private val session: SharedPreferences.Editor? by lazy { sharedPreferences.edit() }

    override fun isDialogConfirm() {}

    override fun layoutContentView(): Int = R.layout.activity_main

    override fun doInjection(appComponent: ApplicationComponent) = appComponent.inject(this)

    override fun checkSession() {
        session?.let {
            when (sharedPreferences.getBoolean(MainActivity.KEY_CHECK_LOGIN, false)) {
                true -> startActivity(Intent(this, HomeActivity::class.java))
            }
        }
    }

    override fun onError(message: Int) {
        loadingSuccess()
        Toast.makeText(applicationContext, applicationContext.getString(message), Toast.LENGTH_LONG).show()
    }

    override fun successLogin(data: DataLogin) {
        loadingSuccess()
        logIn(data.email)
        startActivity(Intent(this, HomeActivity::class.java))
        Toast.makeText(applicationContext, "Success BITCH!!!", Toast.LENGTH_LONG).show()
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

    override fun logOut() {
        session?.clear()
        session?.putBoolean(MainActivity.KEY_CHECK_LOGIN, false)
    }

    override fun logIn(email: String?) {
        session?.putString(MainActivity.KEY_SESSION_EMAIL, email)
        session?.putBoolean(MainActivity.KEY_CHECK_LOGIN, true)
        session?.commit()
    }

    companion object {
        const val KEY_SESSION_EMAIL = "session_email"
        const val KEY_CHECK_LOGIN = "lul"
    }
}