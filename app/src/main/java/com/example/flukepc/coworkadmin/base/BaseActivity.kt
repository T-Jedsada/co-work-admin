@file:Suppress("DEPRECATION")

package com.example.flukepc.coworkadmin.base

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.ProgressDialog
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import com.example.flukepc.coworkadmin.App
import com.example.flukepc.coworkadmin.R
import com.example.flukepc.coworkadmin.di.ApplicationComponent
import javax.inject.Inject

@Suppress("DEPRECATION")
@SuppressLint("Registered", "CommitPrefEdits")
abstract class BaseActivity<V : BaseContractor.View, P : BaseContractor.Presenter<V>> :
        BaseContractor.View, AppCompatActivity() {

    @Inject
    protected lateinit var presenter: P

    private lateinit var progressDialog: ProgressDialog

    @LayoutRes
    protected abstract fun layoutContentView(): Int

    protected abstract fun doInjection(appComponent: ApplicationComponent)

    protected abstract fun setupView()

    protected abstract fun checkSession()

    protected abstract fun logIn(email: String?)

    protected abstract fun logOut()

    protected abstract fun isDialogConfirm()

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        doInjection(App.appComponent)
        setContentView(layoutContentView())
        presenter.attachView(this as V)
        checkSession()
        setupView()
    }

    protected fun loadDialog() {
        progressDialog = ProgressDialog.show(this, "Loading", "Loading Content...", false, false)
        progressDialog.show()
    }

    protected fun loadingSuccess() {
        progressDialog.dismiss()
    }

    protected fun showDialog(message: String) {
        AlertDialog.Builder(this)
                .setMessage(message)
                .setTitle(getString(R.string.txt_warn))
                .setPositiveButton(getString(R.string.txt_confirm_dialog), { _, _ ->
                    isDialogConfirm()
                }).setNegativeButton(getString(R.string.txt_reject_dialog), null).show()
    }
}