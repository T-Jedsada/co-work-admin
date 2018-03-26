@file:Suppress("DEPRECATION")

package com.example.flukepc.coworkadmin.base

import android.app.AlertDialog
import android.app.ProgressDialog
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.flukepc.coworkadmin.App
import com.example.flukepc.coworkadmin.R
import com.example.flukepc.coworkadmin.di.ApplicationComponent
import javax.inject.Inject

@Suppress("DEPRECATION")
abstract class BaseFragment<V : BaseContractor.View, P : BaseContractor.Presenter<V>> :
        BaseContractor.View, Fragment() {
    @Inject
    protected lateinit var presenter: P

    private lateinit var progressDialog: ProgressDialog

    @LayoutRes
    protected abstract fun layoutInflate(): Int

    protected abstract fun doInjection(appComponent: ApplicationComponent)

    protected abstract fun setAdapter()

    protected abstract fun initFunction()

    protected abstract fun isDialogConfirm(id: String?=null, option: String?=null)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? = inflater.inflate(layoutInflate()
            , container, false)

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        doInjection(App.appComponent)
        presenter.attachView(this as V)
        initFunction()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
    }

    protected fun loadDialog() {
        progressDialog = ProgressDialog.show(context, "Loading", "Loading Content...", false, false)
        progressDialog.show()
    }

    protected fun loadingSuccess() {
        progressDialog.dismiss()
    }

    protected fun showDialog(message: String, id: String?=null, option: String?=null) {
        AlertDialog.Builder(context)
                .setMessage(message)
                .setTitle(getString(R.string.txt_warn))
                .setPositiveButton(getString(R.string.txt_confirm_dialog), { _, _ ->
                    isDialogConfirm(id ,option)
                }).setNegativeButton(getString(R.string.txt_reject_dialog), null).show()
    }
}