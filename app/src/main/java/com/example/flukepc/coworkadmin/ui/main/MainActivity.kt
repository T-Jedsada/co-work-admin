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

@SuppressLint("Registered")
class MainActivity : BaseActivity<MainContractor.View, MainPresenter>(), MainContractor.View {

    override fun onError(message: Int) {
        Toast.makeText(applicationContext, applicationContext.getString(message), Toast.LENGTH_LONG).show()
    }

    override fun successLogin(data: DataLogin) {
        startActivity(Intent(this, HomeActivity::class.java))
        Toast.makeText(applicationContext, "Success BITCH!!!", Toast.LENGTH_LONG).show()
    }

    override fun successFormVerify(email: String, password: String) {
        presenter.callApi(email , password)
    }

    override fun layoutContentView(): Int = R.layout.activity_main

    override fun doInjection(appComponent: ApplicationComponent) = appComponent.inject(this)

    override fun setupView() {
        btnSubmit.setOnClickListener {
            presenter.validateForm(edtUsername.text?.trim().toString(), edtPass.text?.trim().toString())
        }
    }
}