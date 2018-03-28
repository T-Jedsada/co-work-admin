package com.android.cowork.admin.di

import com.android.cowork.admin.di.network.ApiManager
import com.android.cowork.admin.di.network.ApiModule
import com.android.cowork.admin.ui.comment.CommentActivity
import com.android.cowork.admin.ui.detail.DetailActivity
import com.android.cowork.admin.ui.home.HomeActivity
import com.android.cowork.admin.ui.home.approve.ApproveFragment
import com.android.cowork.admin.ui.home.comment.CommentFragment
import com.android.cowork.admin.ui.main.MainActivity
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
}