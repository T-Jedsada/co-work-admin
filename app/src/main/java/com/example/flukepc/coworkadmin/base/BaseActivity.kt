package com.example.flukepc.coworkadmin.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import com.example.flukepc.coworkadmin.App
import com.example.flukepc.coworkadmin.di.ApplicationComponent
import javax.inject.Inject

@SuppressLint("Registered")
abstract class BaseActivity<V : BaseContractor.View, P : BaseContractor.Presenter<V>> :
        BaseContractor.View, AppCompatActivity() {
    @Inject
    protected lateinit var presenter: P

    @LayoutRes
    protected abstract fun layoutContentView(): Int

    protected abstract fun doInjection(appComponent: ApplicationComponent)

    protected abstract fun setupView()

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        doInjection(App.appComponent)
        setContentView(layoutContentView())
        presenter.attachView(this as V)
        setupView()
    }
}