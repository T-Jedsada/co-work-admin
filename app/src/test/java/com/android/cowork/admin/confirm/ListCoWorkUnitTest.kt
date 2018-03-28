package com.android.cowork.admin.confirm

import com.android.cowork.admin.R
import com.android.cowork.admin.base.network.BaseService
import com.android.cowork.admin.model.ListCoWork
import com.android.cowork.admin.request.Request
import com.android.cowork.admin.ui.home.approve.ApproveContact
import com.android.cowork.admin.ui.home.approve.ApprovePresenter
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import retrofit2.Response
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class ApproveUnitTest{

    private val api = mock(BaseService::class.java)
    @Inject
    private var approvePresenter: ApprovePresenter

    @InjectMocks
    private val view = mock(ApproveContact.View::class.java)
    private val request = Request(api)

    init {
        MockitoAnnotations.initMocks(this)
        approvePresenter = ApprovePresenter(request)
        approvePresenter.attachView(view)
    }

    @Before
    fun setUp() {
        val immediate = object : Scheduler() {
            override fun scheduleDirect(@NonNull run: Runnable, delay: Long, @NonNull unit: TimeUnit): Disposable {
                return super.scheduleDirect(run, 0, unit)
            }

            override fun createWorker(): Worker {
                return ExecutorScheduler.ExecutorWorker(Executor { it.run() })
            }
        }
        RxJavaPlugins.setInitIoSchedulerHandler { immediate }
        RxJavaPlugins.setInitComputationSchedulerHandler { immediate }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { immediate }
        RxJavaPlugins.setInitSingleSchedulerHandler { immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { immediate }
    }

    @Test
    fun showListCoWorkWithSuccessResponseCodeShouldGoSuccessMethod() {
        val response = ListCoWork(true)
        Mockito.`when`(api.requestCoWorkList())
                .thenReturn(Observable.just(Response.success(response)))
        approvePresenter.callListCoWorkApi()
        Mockito.verify(view, Mockito.times(1)).successCallback(response)
    }

    @Test
    fun testRequestApiWhenApiUnauthorizedShouldGoToMethodShowErrorOnViewClassForShowSomeMessage() {
        Mockito.`when`(api.requestCoWorkList())
                .thenReturn(Observable.just(Response.error(401, ResponseBody.create(MediaType.parse("text"), "Unauthorized"))))
        approvePresenter.callListCoWorkApi()
        Mockito.verify(view, Mockito.times(1)).onError(R.string.http_wrong_connect_401)
    }

    @Test
    fun testRequestApiWhenApiNotFoundShouldGoToMethodShowErrorOnViewClassForShowSomeMessage() {
        Mockito.`when`(api.requestCoWorkList())
                .thenReturn(Observable.just(Response.error(404, ResponseBody.create(MediaType.parse("text"), "NOT FOUND"))))
        approvePresenter.callListCoWorkApi()
        Mockito.verify(view, Mockito.times(1)).onError(R.string.http_wrong_connect_404)
    }

    @Test
    fun testRequestApiWhenApiInternalServerErrorShouldGoToMethodShowErrorOnViewClassForShowSomeMessage() {
        Mockito.`when`(api.requestCoWorkList())
                .thenReturn(Observable.just(Response.error(500, ResponseBody.create(MediaType.parse("text"), "Internal Server Error"))))
        approvePresenter.callListCoWorkApi()
        Mockito.verify(view, Mockito.times(1)).onError(R.string.http_wrong_connect_500)
    }
}