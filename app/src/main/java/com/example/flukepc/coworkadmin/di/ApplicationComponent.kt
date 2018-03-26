package com.example.flukepc.coworkadmin.di

import com.example.flukepc.coworkadmin.di.network.ApiManager
import com.example.flukepc.coworkadmin.di.network.ApiModule
import com.example.flukepc.coworkadmin.ui.comment.CommentActivity
import com.example.flukepc.coworkadmin.ui.comment.adapter.CommentAdapter
import com.example.flukepc.coworkadmin.ui.confirm.ConfirmActivity
import com.example.flukepc.coworkadmin.ui.detail.DetailActivity
import com.example.flukepc.coworkadmin.ui.home.HomeActivity
import com.example.flukepc.coworkadmin.ui.home.approve.ApproveFragment
import com.example.flukepc.coworkadmin.ui.home.comment.CommentFragment
import com.example.flukepc.coworkadmin.ui.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(ApiManager::class), (ApiModule::class), (AndroidModule::class)])
interface ApplicationComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(homeActivity: HomeActivity)
    fun inject(approveFragment: ApproveFragment)
    fun inject(commentFragment: CommentFragment)
    fun inject(commentActivity: CommentActivity)
    fun inject(detailActivity: DetailActivity)
    fun inject(confirmActivity: ConfirmActivity)
}